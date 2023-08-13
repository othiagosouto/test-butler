pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

include(":file-butler")
include(":webserver")
include(":compose-robots")
rootProject.name = "Test butler"
