package com.github.othiagosouto.file_butler.moshi

import com.github.othiagosouto.file_butler.readFile
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

inline fun <reified T> parse(moshi: Moshi, path: String): T {
    val file = readFile("$path.json")
    val adapter = moshi.adapter(T::class.java)
    return adapter.fromJson(file)!!
}

inline fun <reified T : Any> Moshi.parseFrom(path: String): T = parse(this, path)

inline fun <reified T> parseList(moshi: Moshi, path: String): List<T> {
    val file = readFile("$path.json")
    val adapter: JsonAdapter<List<T>> = moshi.adapter(
        Types.newParameterizedType(
            MutableList::class.java,
            T::class.java
        )
    )
    return adapter.fromJson(file)!!
}

inline fun <reified T> Moshi.parseListFrom(path: String): List<T> = parseList(this, path)
