package dev.thiagosouto.butler.file.moshi

import dev.thiagosouto.butler.file.readFile
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * A standalone method that will convert a json file to a [T] object
 *
 * @param moshi is the [Moshi] instance that will be used to deserialize the content from the json file
 * @param path is the relative path the .json without resources, file from resources or test resources
 * @return object deserialized as [T]
 */
inline fun <reified T> parse(moshi: Moshi, path: String): T {
    val file = readFile("$path.json")
    val adapter = moshi.adapter(T::class.java)
    return adapter.fromJson(file)!!
}

/**
 * A [Moshi] extension that will convert a json file to a [T] object
 *
 * @param path is the relative path the .json without resources, file from resources or test resources
 * @return object deserialized as [T]
 */
inline fun <reified T : Any> Moshi.parseFrom(path: String): T =
    parse(this, path)

/**
 * A method that will convert a json file to a [List]<[T]> object
 *
 * @param moshi is the [Moshi] instance that will be used to deserialize the content from the json file
 * @param path is the relative path the .json without resources, file from resources or test resources
 * @return object deserialized as [List]<[T]>
 */
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

/**
 * A method that will convert a json file to a [List]<[T]> object
 *
 * @param path is the relative path the .json without resources, file from resources or test resources
 * @return object deserialized as [List]<[T]>
 */
inline fun <reified T> Moshi.parseListFrom(path: String): List<T> =
    parseList(this, path)
