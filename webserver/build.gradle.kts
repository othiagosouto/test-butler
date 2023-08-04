
plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("maven-publish")
    id("signing")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    api(Deps.square.mockWebserver)
    api(Deps.square.okhttp)
    api(project(":file-butler"))
}

group = "dev.thiagosouto"
version = rootProject.file("VERSION.txt").readText()

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set("webserver")
                description.set("A library to abstract MockWebServer for testing")
                url.set("https://github.com/othiagosouto/test-butler")

                licenses {
                    license {
                        name.set( "The Apache License, Version 2.0")
                        url.set( "http://www.apache.org/licenses/LICENSE-2.0.txt")
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
                    url.set("https://github.com/othiagosouto/test-butler/webserver")
                }
            }
        }
    }

    repositories {
        maven {
            val releasesRepoUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://oss.sonatype.org/content/repositories/snapshots/"
            url = uri(if((version as String).contains("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            credentials{
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
    sign(publishing.publications["mavenJava"])
}
