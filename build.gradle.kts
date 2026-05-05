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
}

subprojects {
    apply(
        plugin =
            rootProject.libs.plugins.detekt
                .get()
                .pluginId,
    )
    apply(
        plugin =
            rootProject.libs.plugins.kotlin.dokka
                .get()
                .pluginId,
    )

    dependencies {
        "detektPlugins"(rootProject.libs.detekt.ktlint)
    }
}
