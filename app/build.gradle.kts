@file:Suppress("UnstableApiUsage")

import util.ensureVersionCode

plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
    id(Plugins.ksp) version Versions.kspPlugin
    id(Plugins.hilt)
    kotlin(Plugins.serialization) version Versions.kotlin

    id(Plugins.androidGitVersion) version Versions.androidGitVersionPlugin
    id(Plugins.detekt) version Versions.detektPlugin
    id(Plugins.checkDependencyUpdates) version Versions.checkDependencyUpdatesPlugin
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
        release {
            isMinifyEnabled = true
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
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }

    packagingOptions {
        resources {
            excludes.add("META-INF/{AL2.0,LGPL2.1,LICENSE.md,LICENSE-notice.md}")
        }
    }
}

dependencies {
    implementation(Dependencies.jetpackCore)
    implementation(Dependencies.splashScreen)
    implementation(Dependencies.activity)
    implementation(Dependencies.lifecycle)
    implementation(Dependencies.navigation)

    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeIcons)
    debugImplementation(Dependencies.composeTooling)
    implementation(Dependencies.composeToolingPreview)
    debugImplementation(Dependencies.composeTestManifest)

    implementation(Dependencies.coroutines)
    implementation(Dependencies.serialization)
    implementation(Dependencies.immutableCollections)

    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)
    implementation(Dependencies.hiltNavigationCompose)

    implementation(Dependencies.dataStorePreferences)
    implementation(Dependencies.room)
    ksp(Dependencies.roomCompiler)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitSerializationConverter)

    implementation(Dependencies.coil)
    implementation(Dependencies.accompanistSystemUi)

    implementation(Dependencies.timber)
    debugImplementation(Dependencies.leakCanary)

    // Test

    testImplementation(Dependencies.junit)

    androidTestImplementation(Dependencies.jetpackJunit)
    androidTestImplementation(Dependencies.composeJunit)
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
    }
}
