plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}
apply(from = "$rootDir/feature-dependencies.gradle")

android {
    namespace = "com.enrech.tarisearch"
    compileSdk = Integer.parseInt(libs.versions.compile.sdk.get())
    buildToolsVersion = libs.versions.build.tools.get()

    defaultConfig {
        val appVersionMajor = Integer.parseInt(libs.versions.app.version.major.get())
        val appVersionMinor = Integer.parseInt(libs.versions.app.version.minor.get())
        val appVersionFix = Integer.parseInt(libs.versions.app.version.fix.get())
        val appVersionBuild = 0
        val appVersionCode = appVersionMajor * 1000000 + appVersionMinor * 10000 + appVersionFix * 100 + appVersionBuild
        val appVersionName = "$appVersionMajor.$appVersionMinor.$appVersionFix"
        applicationId = "com.enrech.tarisearch"
        minSdk = Integer.parseInt(libs.versions.min.sdk.get())
        targetSdk = Integer.parseInt(libs.versions.target.sdk.get())
        versionCode = appVersionCode
        versionName = appVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
        freeCompilerArgs = listOf(
            *freeCompilerArgs.toTypedArray(),
            "-opt-in=kotlin.time.ExperimentalTime",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
        )
    }

    buildFeatures.compose = true
    buildFeatures.buildConfig = true
    composeOptions.kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()

    sourceSets.getByName("main") {
        java.srcDir("src/main/kotlin")
    }
}

dependencies {
}