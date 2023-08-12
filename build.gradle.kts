plugins {
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin.gradle.plugin)
    alias(libs.plugins.kotlin.dokka) apply false
}

allprojects {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }

    apply(plugin = rootProject.libs.plugins.detekt.get().pluginId)
    apply(plugin = rootProject.libs.plugins.kotlin.dokka.get().pluginId)

    dependencies {

        this.dependencies.add("detektPlugins", provider { libs.detekt.formatting.get() })
        this.dependencies.add("dokkaHtmlPlugin", provider { libs.plugins.kotlin.dokka.get() })
    }
}
