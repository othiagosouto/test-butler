plugins {
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin.gradle)
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
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
        //https://github.com/gradle/gradle/issues/22468#issuecomment-1632409263
        this.dependencies.add("detektPlugins", provider { libs.detekt.formatting.get() })
        this.dependencies.add("dokkaHtmlPlugin", provider { libs.plugins.kotlin.dokka.get() })
    }
}
