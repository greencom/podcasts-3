@file:Suppress("UnstableApiUsage")

import buildtypes.appBuildTypes
import utils.ensureVersionCode

plugins {
    id(Plugins.Android.application) version Versions.Plugins.androidGradle
    id(Plugins.Kotlin.android) version Versions.Kotlin.kotlin
    id(Plugins.Kotlin.kapt) version Versions.Kotlin.kotlin
    id(Plugins.Kotlin.ksp) version Versions.Kotlin.ksp
    id(Plugins.Kotlin.serialization) version Versions.Kotlin.kotlin
    id(Plugins.hilt) version Versions.Hilt.hilt
    id(Plugins.googleServices) version Versions.Plugins.googleServices

    id(Plugins.androidGitVersion) version Versions.Plugins.androidGitVersion
    id(Plugins.detekt) version Versions.Plugins.detekt
    id(Plugins.checkDependencyUpdates) version Versions.Plugins.checkDependencyUpdates
}

kapt {
    correctErrorTypes = true
}

androidGitVersion {
    format = "%tag%--%branch%--%commit%"
}

detekt {
    buildUponDefaultConfig = true
    config = files("../config/detekt-config.yml")
}

android {
    val mApplicationId = "com.greencom.android.podcasts3"

    namespace = mApplicationId
    compileSdk = 33

    defaultConfig {
        applicationId = mApplicationId
        minSdk = 24
        targetSdk = 33
        versionCode = ensureVersionCode(androidGitVersion.code())
        versionName = androidGitVersion.name()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    ksp {
        arg("room.schemaLocation", "$projectDir/room_schemas")
    }

    val listenNotesApiKeyHolder = ListenNotesApiKeyHolder.init(project)

    buildTypes {
        appBuildTypes.forEach { buildType ->
            maybeCreate(buildType.name).apply {
                isDebuggable = buildType.isDebuggable
                isMinifyEnabled = buildType.isMinifyEnabled
                applicationIdSuffix = buildType.applicationIdSuffix
                versionNameSuffix = buildType.versionNameSuffix

                stringResValue(Keys.APP_NAME, buildType.appName)
                booleanBuildConfigField(Keys.IS_LOGGING_ENABLED, buildType.isLoggingEnabled)
            }
        }

        all {
            stringBuildConfigField(Keys.LISTEN_NOTES_API_KEY, listenNotesApiKeyHolder.key)

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Jetpack.Compose.compiler
    }

    packagingOptions {
        resources {
            excludes.add("META-INF/{AL2.0,LGPL2.1,LICENSE.md,LICENSE-notice.md}")
        }
    }
}

dependencies {
    implementation(Dependencies.Jetpack.core)
    implementation(Dependencies.Jetpack.activity)
    implementation(Dependencies.Jetpack.lifecycle)
    implementation(Dependencies.Jetpack.navigation)
    implementation(Dependencies.Jetpack.dataStorePreferences)
    implementation(Dependencies.Jetpack.room)
    ksp(Dependencies.Jetpack.roomCompiler)
    implementation(Dependencies.Jetpack.splashScreen)

    implementation(platform(Dependencies.Jetpack.Compose.bom))
    implementation(Dependencies.Jetpack.Compose.ui)
    implementation(Dependencies.Jetpack.Compose.material)
    implementation(Dependencies.Jetpack.Compose.material3)
    implementation(Dependencies.Jetpack.Compose.icons)
    implementation(Dependencies.Jetpack.Compose.toolingPreview)
    debugImplementation(Dependencies.Jetpack.Compose.tooling)
    debugImplementation(Dependencies.Jetpack.Compose.testManifest)

    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.Kotlin.serialization)
    implementation(Dependencies.Kotlin.immutableCollections)

    implementation(Dependencies.Hilt.hilt)
    kapt(Dependencies.Hilt.compiler)
    implementation(Dependencies.Hilt.navigationCompose)

    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.serializationConverter)
    implementation(Dependencies.Retrofit.loggingInterceptor)

    implementation(Dependencies.coil)
    implementation(Dependencies.Accompanist.systemUi)

    implementation(platform(Dependencies.Firebase.bom))
    implementation(Dependencies.Firebase.auth)

    implementation(Dependencies.timber)
    debugImplementation(Dependencies.leakCanary)

    // Test

    testImplementation(Dependencies.junit)

    androidTestImplementation(Dependencies.Jetpack.junit)
    androidTestImplementation(Dependencies.Jetpack.Compose.junit)
}

// Compose metrics
// Command: ./gradlew assembleRelease -P.enableComposeCompilerReports=true --rerun-tasks
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    kotlinOptions.freeCompilerArgs += listOf(
        "-P",
        "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                "${project.buildDir.absolutePath}/compose_metrics"
    )
    kotlinOptions.freeCompilerArgs += listOf(
        "-P",
        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                "${project.buildDir.absolutePath}/compose_metrics"
    )
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true)
        xml.required.set(false)
        txt.required.set(false)
        sarif.required.set(false)
        md.required.set(false)
    }
}

fun com.android.build.api.dsl.VariantDimension.stringResValue(name: String, value: String) {
    resValue("string", name, value)
}

fun com.android.build.api.dsl.VariantDimension.stringBuildConfigField(name: String, value: String) {
    buildConfigField("String", name, "\"$value\"")
}

fun com.android.build.api.dsl.VariantDimension.booleanBuildConfigField(name: String, value: Boolean) {
    buildConfigField("boolean", name, "$value")
}
