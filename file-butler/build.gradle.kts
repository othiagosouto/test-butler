plugins {
    alias(libs.plugins.kotlin.gradle)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.maven.publish.get().pluginId)
    id(libs.plugins.signing.plugin.get().pluginId)
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.gson)
    implementation(libs.moshi)
    kapt(libs.moshi.codegen)

    testImplementation(libs.test.truth)
    testImplementation(libs.test.junit)
    kaptTest(libs.moshi.codegen)
}

sourceSets.getByName("main") {
    java.srcDir("src/main/java")
    java.srcDir("src/main/kotlin")
}
sourceSets.getByName("test") {
    java.srcDir("src/test/java")
    java.srcDir("src/test/kotlin")
}

val groupIdToPublish =  rootProject.file("GROUP_ID.txt").readText()
val versionToPublish =  rootProject.file("VERSION.txt").readText()

group = groupIdToPublish
version = versionToPublish

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = groupIdToPublish
            artifactId = "file-butler"
            version = versionToPublish
            pom {
                packaging = "jar"
                name.set("file-butler")
                description.set("A library to support TDD practice")
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
                    url.set("https://github.com/othiagosouto/test-butler/file-butler")
                }
            }
        }
    }

    repositories {
        maven {
            val releasesRepoUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://oss.sonatype.org/content/repositories/snapshots/"

            url = uri(if(versionToPublish.contains("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)

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
    sign(publishing.publications["mavenJava"])
}