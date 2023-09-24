plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

apply(from = "$rootDir/android-config.gradle")
apply(from = "$rootDir/feature-dependencies.gradle")
apply(from = "$rootDir/test-dependencies.gradle")

android.namespace = "com.enrech.tarisearch.main"

dependencies {
    implementation(project(":common"))
    implementation(project((":marker-domain")))
    implementation(project((":api-data")))

    implementation(libs.google.maps.compose)
    implementation(libs.google.maps.services)
}