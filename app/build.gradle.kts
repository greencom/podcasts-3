@file:Suppress("UnstableApiUsage")

import util.ensureVersionCode

plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)

    id(Plugins.androidGitVersion) version Versions.androidGitVersionPlugin
    id(Plugins.detekt) version Versions.detektPlugin
    id(Plugins.checkDependencyUpdates) version Versions.checkDependencyUpdatesPlugin
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

        // TODO: Export Room schemas
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
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    packagingOptions {
        resources {
            excludes.add("META-INF/{AL2.0,LGPL2.1,LICENSE.md,LICENSE-notice.md}")
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui:1.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.0")
    implementation("androidx.compose.material3:material3:1.0.0-alpha11")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.0")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.0")
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true)
        xml.required.set(false)
        txt.required.set(false)
    }
}
