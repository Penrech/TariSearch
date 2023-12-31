plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("plugin.serialization")
}

apply(from = "$rootDir/android-config.gradle")
apply(from = "$rootDir/feature-dependencies.gradle")
apply(from = "$rootDir/test-dependencies.gradle")

android.namespace = "com.enrech.ulessontest.api_data"

dependencies {
    api(project(":common-data"))
    api(project(":api-domain"))

    implementation(libs.kotlin.serialization.json)
}