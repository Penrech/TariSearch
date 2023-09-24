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

android.namespace = "com.enrech.tarisearch.marker_domain"

dependencies {
    implementation(project(":api-domain"))
    implementation(project(":common-domain"))
}