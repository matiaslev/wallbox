/**
 * Modules dependencies source of truth
 * Modules gradle configuration source of truth
 */

object Libs {

    object Androidx {
        object Compose {
            const val version = "1.2.0-rc02"
            const val ui = "androidx.compose.ui:ui:$version"
            const val material = "androidx.compose.material:material:$version"
            const val preview = "androidx.compose.ui:ui-tooling-preview:$version"
            const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$version"

            const val navigation = "androidx.navigation:navigation-compose:2.4.2"
            const val activity = "androidx.activity:activity-compose:1.4.0"
        }

        const val core = "androidx.core:core-ktx:1.8.0"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
    }

    object Coroutines {
        private const val version = "1.6.3"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        const val testing = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    const val chart = "com.github.PhilJay:MPAndroidChart:v3.1.0"

    object Lottie {
        private const val version = "5.0.3"
        const val lottie = "com.airbnb.android:lottie:$version"
        const val lottieCompose = "com.airbnb.android:lottie-compose:$version"
    }

    object Koin {
        private const val version = "3.2.0"
        const val android = "io.insert-koin:koin-android:3.2.0"
        const val navigation = "io.insert-koin:koin-androidx-navigation:3.2.0"
        const val compose = "io.insert-koin:koin-androidx-compose:3.1.4"
    }

    object Testing {
        const val junit = "junit:junit:4.13.2"
        const val extJunit = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
        const val composeUi = "androidx.compose.ui:ui-test-junit4:1.2.0-rc02"
        const val assertj = "org.assertj:assertj-core:3.11.1"
        const val mockk = "io.mockk:mockk:1.12.1"
    }

    object DebugImplementation {
        const val uiTooling = "androidx.compose.ui:ui-tooling:1.2.0-rc02"
        const val uiTest = "androidx.compose.ui:ui-test-manifest:1.2.0-rc02"
    }

    const val gson = "com.google.code.gson:gson:2.9.0"
}

object Configuration {
    const val compileSdk = 32
    const val minSdk = 26
    const val targetSdk = 32
    const val versionCode = 1
    const val versionName = "1.0"
    const val jvmTarget = "1.8"
}