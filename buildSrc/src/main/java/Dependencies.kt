object Dependencies {

    const val jetpackCore = "androidx.core:core-ktx:${Versions.jetpackCore}"
    const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"
    const val activity = "androidx.activity:activity-compose:${Versions.activity}"
    const val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"

    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial3 =
        "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    const val composeIcons =
        "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"

    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val serialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
    const val immutableCollections =
        "org.jetbrains.kotlinx:kotlinx-collections-immutable:${Versions.immutableCollections}"

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltNavigationCompose =
        "androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeNavigation}"

    const val dataStorePreferences =
        "androidx.datastore:datastore-preferences:${Versions.dataStore}"
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitSerializationConverter =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitSerializationConverter}"

    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val accompanistSystemUi =
        "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    const val junit = "junit:junit:${Versions.junit}"
    const val jetpackJunit = "androidx.test.ext:junit:${Versions.jetpackJunit}"

}
