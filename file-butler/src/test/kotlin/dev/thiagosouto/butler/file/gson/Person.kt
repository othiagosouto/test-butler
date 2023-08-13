package dev.thiagosouto.butler.file.gson

import com.google.gson.annotations.SerializedName

internal data class Person(val name: String, @SerializedName("last_name") val lastName: String, val age: Int)
