package dev.thiagosouto.butler.file.serialization

import kotlinx.serialization.SerialName

internal data class PersonSerialization(val name: String, @SerialName("last_name") val lastName: String, val age: Int)
