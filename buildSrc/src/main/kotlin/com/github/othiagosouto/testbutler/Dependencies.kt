
class Test {
    val jUnit = "junit:junit:4.13.1"
    val truth = "com.google.truth:truth:1.1"
}

class Core {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.20"
}

class Utils {
    val dokka = "org.jetbrains.dokka:kotlin-as-java-plugin:1.4.20"
    val gson = "com.google.code.gson:gson:2.8.6"
    val moshi = "com.squareup.moshi:moshi:1.11.0"
    val moshCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:1.11.0"
}

object Deps {
    val core = Core()
    val testing = Test()
    val utils = Utils()
}

