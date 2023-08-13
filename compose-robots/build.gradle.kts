plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
    id("signing")
}

group = "dev.thiagosouto"
version = rootProject.file("VERSION.txt").readText()

android {
    namespace = "dev.thiagosouto.compose.robots"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = false
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    publishing {
        singleVariant("release") {
            withJavadocJar()
            withSourcesJar()
        }
    }
}

dependencies {
    compileOnly(platform(libs.android.compose.bom))
    compileOnly(libs.test.android.compose)
}

group = "dev.thiagosouto"
version = rootProject.file("VERSION.txt").readText()

publishing {
    publications {
        register<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }
            groupId = "dev.thiagosouto"
            artifactId = "compose-robots"
            version = rootProject.file("VERSION.txt").readText()

            pom {
                packaging = "aar"
                name.set("Compose Robots")
                description.set("A library to support creating composable robots")
                url.set("https://github.com/othiagosouto/test-butler")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("othiagosouto")
                        name.set("Thiago Souto")
                        email.set("thiagosouto@duck.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/othiagosouto/test-butler.git/")
                    developerConnection.set("scm:git:ssh://github.com:othiagosouto/test-butler.git")
                    url.set("https://github.com/othiagosouto/test-butler/compose-robots")
                }
            }
        }
    }

    repositories {
        maven {
            val version = rootProject.file("VERSION.txt").readText()
            val releasesRepoUrl =
                "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://oss.sonatype.org/content/repositories/snapshots/"

            url = uri(if (version.contains("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)

            credentials {
                username = System.getenv("ORG_GRADLE_PROJECT_sonatypeUsername")
                password = System.getenv("ORG_GRADLE_PROJECT_sonatypePassword")
            }
        }
    }
}


signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications["release"])
}
