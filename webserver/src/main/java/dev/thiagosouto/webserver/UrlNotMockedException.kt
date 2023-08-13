package dev.thiagosouto.webserver

/**
 * Thrown to indicate that a request to a not mocked url has been made.
 */
class UrlNotMockedException(url: String) : Exception("Url $url is not mocked")
