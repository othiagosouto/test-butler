// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        mavenCentral()
        google()
        jcenter()
    }
    dependencies {
        classpath(Deps.core.androidGradlePlugin)
        classpath(Deps.core.kotlinGradlePlugin)
        classpath(Deps.core.detektPlugin)
        classpath(Deps.utils.dokkaPlugin)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "org.jetbrains.dokka")
}

plugins{
    id("io.gitlab.arturbosch.detekt") version "1.23.1" apply false
    id("org.jetbrains.dokka") version "1.8.20" apply false
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}

