plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
}

apply(from = "$rootDir/android-config.gradle")
apply(from = "$rootDir/feature-dependencies.gradle")
apply(from = "$rootDir/test-dependencies.gradle")

android.namespace = "com.enrech.ulessontest.common_data"

dependencies {
    api(project(":common-domain"))
    api(libs.ktor.client.android)
    api(libs.ktor.content.negotiation)
    api(libs.ktor.client.logging)
    api(libs.ktor.client.serialization.json)
    api(libs.kotlin.serialization.json)

    api(libs.androidx.room.runtime)
    api(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}