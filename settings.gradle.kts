pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TariSearch"
include(":app")
include(":common-domain")
include(":common-data")
include(":common")
include(":common-resources")
include(":main")
include(":api-domain")
include(":api-data")
