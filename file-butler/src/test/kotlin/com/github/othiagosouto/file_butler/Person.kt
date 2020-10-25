package com.github.othiagosouto.file_butler

import com.google.gson.annotations.SerializedName

data class Person(val name: String, @SerializedName("last_name") val lastName: String, val age: Int)