package com.github.othiagosouto.butler.file

import java.io.FileNotFoundException
import java.net.URL

fun readFile(path: String): String {
    val content: URL? = ClassLoader.getSystemResource(path)

    return content?.readText() ?: throw FileNotFoundException("file path: $path was not found")
}