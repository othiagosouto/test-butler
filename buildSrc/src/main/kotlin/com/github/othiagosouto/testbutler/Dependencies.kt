class Test {
    val jUnit = "junit:junit:4.13.1"
    val truth = "com.google.truth:truth:1.1"
}

class Core {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.10"
    val detektPlugin = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.1"
    val ktlintDetekt = "io.gitlab.arturbosch.detekt:detekt-formatting:1.23.1"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10"

}

class Utils {
    val dokkaPlugin = "org.jetbrains.dokka:dokka-gradle-plugin:1.8.10"
    val gson = "com.google.code.gson:gson:2.10.1"
    val moshi = "com.squareup.moshi:moshi:1.15.0"
    val moshCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:1.15.0"
}

class Square {
    val mockWebserver = "com.squareup.okhttp3:mockwebserver:4.9.3"
    val okhttp = "com.squareup.okhttp3:okhttp:4.9.3"
}

object Deps {
    val core = Core()
    val testing = Test()
    val utils = Utils()
    val square = Square()
}

