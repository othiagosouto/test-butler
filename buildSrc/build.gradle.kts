plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    jcenter()
}

dependencies {
    implementation(gradleApi())
}

gradlePlugin {
    plugins {
        register("dependencies") {
            id = "com.github.othiagosouto.testbutler.dependencies"
            implementationClass = "com.github.othiagosouto.testbutler.Dependencies"
        }
    }
}