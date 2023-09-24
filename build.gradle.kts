buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://repo.repsy.io/mvn/chrynan/public") }
    }
    dependencies {
        classpath(libs.gradle.plugin)
        classpath(libs.kotlin.plugin)
        classpath(libs.androidx.nav.safe.args.plugin)
        classpath(libs.dagger.plugin)
        classpath(libs.kotlin.serialization.plugin)
    }
}

plugins {
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://repo.repsy.io/mvn/chrynan/public") }
    }
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}