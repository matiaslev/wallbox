plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Libs.Coroutines.android)

    testImplementation(Libs.Coroutines.testing)
    testImplementation(Libs.Testing.junit)
    testImplementation(Libs.Testing.assertj)
    testImplementation(Libs.Testing.mockk)
}