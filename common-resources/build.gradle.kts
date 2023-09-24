plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

apply(from = "$rootDir/android-config.gradle")
apply(from = "$rootDir/feature-dependencies.gradle")

android.namespace = "com.enrech.ulessontest.common_resources"

dependencies {}