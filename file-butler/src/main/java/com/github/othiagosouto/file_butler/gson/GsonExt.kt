package com.github.othiagosouto.file_butler.gson

import com.github.othiagosouto.file_butler.readFile
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> parse(gson: Gson, path: String): T {
    val file = readFile("$path.json")
    return gson.fromJson(file, object : TypeToken<T>() {}.type)
}

inline fun <reified T> Gson.parseFrom(path: String): T = parse(this, path)