package com.github.othiagosouto.file_butler.moshi

import com.github.othiagosouto.file_butler.readFile
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

inline fun <reified T> parse(moshi: Moshi, path: String): T {
    val file = readFile("$path.json")
    val adapter = moshi.adapter(T::class.java)
    return adapter.fromJson(file)!!
}

inline fun <reified T> Moshi.parseFrom(path: String): T {
    val file = readFile("$path.json")
    val adapter: JsonAdapter<T> = this.adapter(T::class.java)
    return adapter.fromJson(file)!!
}