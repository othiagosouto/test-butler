package dev.thiagosouto.butler.file

import java.io.FileNotFoundException
import java.net.URL

/**
 * A standalone method that will read a file and return it's content as Text
 *
 * @throws [java.io.FileNotFoundException] if content is null or path does not exist
 * @param path is the relative path the the file with its extension
 * @return content as Text
 */
fun readFile(path: String): String {
    val content: URL? = ClassLoader.getSystemResource(path)

    return content?.readText() ?: throw FileNotFoundException("file path: $path was not found")
}