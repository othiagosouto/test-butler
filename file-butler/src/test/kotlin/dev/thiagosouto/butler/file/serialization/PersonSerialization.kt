package dev.thiagosouto.butler.file.serialization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PersonSerialization(val name: String, @SerialName("last_name") val lastName: String, val age: Int)
