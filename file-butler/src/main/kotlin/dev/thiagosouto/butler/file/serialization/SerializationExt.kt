package dev.thiagosouto.butler.file.serialization

import dev.thiagosouto.butler.file.readFile
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

inline fun <reified T> parse(json: Json, path: String): T {
    val file = readFile(T::class.java.classLoader, "$path.json")
    return json.decodeFromString(file)
}

inline fun <reified T> Json.parseFrom(path: String): T = parse(this, path)

inline fun <reified T> parseList(json: Json, path: String): List<T> {
    val file = readFile(T::class.java.classLoader, "$path.json")
    return json.decodeFromString<List<T>>(file)
}

inline fun <reified T> Json.parseListFrom(path: String): List<T> = parseList(this, path)
