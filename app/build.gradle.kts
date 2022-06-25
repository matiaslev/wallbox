plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    compileSdk = Configuration.compileSdk

    defaultConfig {
        applicationId = "com.example.matiaslevwallboxchallenge"
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
        jvmTarget = Configuration.jvmTarget
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
    testImplementation(Libs.Testing.mockk)
    testImplementation(Libs.Coroutines.testing)
    testImplementation(Libs.Testing.coreTesting)
    androidTestImplementation(Libs.Testing.extJunit)
    androidTestImplementation(Libs.Testing.espresso)
    androidTestImplementation(Libs.Testing.composeUi)

    debugImplementation(Libs.DebugImplementation.uiTooling)
    debugImplementation(Libs.DebugImplementation.uiTest)
}