package dev.thiagosouto.butler.file.gson

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.thiagosouto.butler.file.readFile

/**
 * A method that will convert a json file to a [T] object
 *
 * @param gson the [com.google.gson.Gson] instance that will be used to build the object from path
 * @param path is the relative path the .json without resources, file from resources or test resources
 * @return object deserialized as type [T]
 */
inline fun <reified T> parse(gson: Gson, path: String): T {
    val file = readFile(T::class.java.classLoader, "$path.json")
    return gson.fromJson(file, object : TypeToken<T>() {}.type)
}
