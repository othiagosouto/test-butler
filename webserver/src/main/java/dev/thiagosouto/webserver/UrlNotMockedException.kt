package dev.thiagosouto.webserver

class UrlNotMockedException(url: String) : Exception("Url $url is not mocked")
