apply(plugin = "java-library")
apply(plugin = "kotlin")
apply(plugin = "kotlin-kapt")

dependencies {
    "implementation"(Deps.core.kotlin)
    "implementation"(Serializers.gson)
    "testImplementation"(Deps.testing.truth)
    "testImplementation"(Deps.testing.jUnit)
    "implementation"(Serializers.moshi)
    "kapt"(Serializers.moshCodeGen)
    "kaptTest"(Serializers.moshCodeGen)
}

private object Serializers {
    const val gson = "com.google.code.gson:gson:2.8.6"
    const val moshi = "com.squareup.moshi:moshi:1.11.0"
    const val moshCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:1.11.0"
}
