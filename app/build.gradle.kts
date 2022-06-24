plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.matiaslevwallboxchallenge"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
        kotlinCompilerExtensionVersion = Libs.Androidx.Compose.version
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Libs.Androidx.Compose.ui)
    implementation(Libs.Androidx.Compose.material)
    implementation(Libs.Androidx.Compose.preview)
    implementation(Libs.Androidx.Compose.navigation)

    implementation(Libs.Androidx.core)
    implementation(Libs.Androidx.lifecycle)
    implementation(Libs.Androidx.lifecycle)

    implementation(Libs.chart)

    implementation(Libs.Lottie.lottie)
    implementation(Libs.Lottie.lottieCompose)

    implementation(Libs.Coroutines.android)

    implementation(Libs.Koin.android)
    implementation(Libs.Koin.navigation)
    implementation(Libs.Koin.compose)

    testImplementation(Libs.Testing.junit)
    androidTestImplementation(Libs.Testing.extJunit)
    androidTestImplementation(Libs.Testing.espresso)
    androidTestImplementation(Libs.Testing.composeUi)

    debugImplementation(Libs.DebugImplementation.uiTooling)
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.0-rc02")
}