package com.github.othiagosouto.file_butler

import java.io.FileNotFoundException
import java.net.URL

fun readFile(path: String): String {
    val content: URL? = ClassLoader.getSystemResource(path)

    return content?.readText() ?: throw FileNotFoundException("file path: $path was not found")
}