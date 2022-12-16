object Dependencies {

    object Jetpack {
        const val core = "androidx.core:core-ktx:${Versions.Jetpack.core}"
        const val activity = "androidx.activity:activity-compose:${Versions.Jetpack.activity}"
        const val lifecycle =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Jetpack.lifecycle}"
        const val navigation =
            "androidx.navigation:navigation-compose:${Versions.Jetpack.navigation}"
        const val dataStorePreferences =
            "androidx.datastore:datastore-preferences:${Versions.Jetpack.dataStore}"
        const val room = "androidx.room:room-ktx:${Versions.Jetpack.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.Jetpack.room}"
        const val splashScreen = "androidx.core:core-splashscreen:${Versions.Jetpack.splashScreen}"
        const val junit = "androidx.test.ext:junit:${Versions.Jetpack.junit}"

        object Compose {
            const val bom = "androidx.compose:compose-bom:${Versions.Jetpack.Compose.bom}"
            const val ui = "androidx.compose.ui:ui"
            const val material = "androidx.compose.material:material"
            const val material3 = "androidx.compose.material3:material3"
            const val icons = "androidx.compose.material:material-icons-extended"
            const val toolingPreview = "androidx.compose.ui:ui-tooling-preview"
            const val tooling = "androidx.compose.ui:ui-tooling"
            const val testManifest = "androidx.compose.ui:ui-test-manifest"
            const val junit =
                "androidx.compose.ui:ui-test-junit4:${Versions.Jetpack.Compose.junit}"
        }
    }

    object Kotlin {
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
        const val serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.serialization}"
        const val immutableCollections =
            "org.jetbrains.kotlinx:kotlinx-collections-immutable:" +
                    "${Versions.Kotlin.immutableCollections}"
    }

    object Hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.Hilt.hilt}"
        const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.Hilt.hilt}"
        const val navigationCompose =
            "androidx.hilt:hilt-navigation-compose:${Versions.Hilt.navigationCompose}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Retrofit.retrofit}"
        const val serializationConverter =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:" +
                    "${Versions.Retrofit.serializationConverter}"
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.Retrofit.loggingInterceptor}"
    }

    object Accompanist {
        const val systemUi =
            "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    }

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
        const val auth = "com.google.firebase:firebase-auth-ktx"
    }

    const val googlePlayServices =
        "com.google.android.gms:play-services-auth:${Versions.googlePlayServices}"

    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    const val junit = "junit:junit:${Versions.junit}"

}
