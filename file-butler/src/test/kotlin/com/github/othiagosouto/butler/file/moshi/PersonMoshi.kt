package com.github.othiagosouto.butler.file.moshi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonMoshi(
    val name: String,
    @Json(name = "last_name") val lastName: String,
    val age: Int
)