@file:Suppress("UnstableApiUsage")

import buildtype.appBuildTypes
import util.ensureVersionCode

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serializtion)
    alias(libs.plugins.hilt)

    alias(libs.plugins.androidGitVersion)
    alias(libs.plugins.detekt)
    alias(libs.plugins.checkDependencyUpdates)
}

kapt {
    correctErrorTypes = true
}

ksp {
    arg("room.schemaLocation", "$projectDir/room_schemas")
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

    buildTypes {
        appBuildTypes.forEach { buildType ->
            maybeCreate(buildType.name).apply {
                isDebuggable = buildType.isDebuggable
                isMinifyEnabled = buildType.isMinifyEnabled
                applicationIdSuffix = buildType.applicationIdSuffix
                versionNameSuffix = buildType.versionNameSuffix

                stringResValue(Keys.appName, buildType.appName)
            }
        }

        all {
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
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    packagingOptions {
        resources {
            excludes.add("META-INF/{AL2.0,LGPL2.1,LICENSE.md,LICENSE-notice.md}")
        }
    }
}

dependencies {
    implementation(libs.jetpack.core)
    implementation(libs.jetpack.splashScreen)
    implementation(libs.jetpack.activity)
    implementation(libs.jetpack.lifecycle)
    implementation(libs.jetpack.navigation)
    implementation(libs.jetpack.dataStorePreferences)
    implementation(libs.jetpack.room)
    ksp(libs.jetpack.roomCompiler)

    implementation(libs.compose.ui)
    implementation(libs.compose.material2)
    implementation(libs.compose.material3)
    implementation(libs.compose.icons)
    debugImplementation(libs.compose.tooling)
    implementation(libs.compose.toolingPreview)
    debugImplementation(libs.compose.testManifest)

    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.serialization)
    implementation(libs.kotlin.immutableCollections)

    implementation(libs.hilt.hilt)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigationCompose)

    implementation(libs.retrofit.retrofit)
    implementation(libs.retrofit.serializationConverter)

    implementation(libs.coil)
    implementation(libs.accompanistSystemUi)

    implementation(libs.debug.timber)
    debugImplementation(libs.debug.leakCanary)

    // Test

    testImplementation(libs.test.junit)

    androidTestImplementation(libs.test.jetpackJunit)
    androidTestImplementation(libs.test.composeJunit)
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
