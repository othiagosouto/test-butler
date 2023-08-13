package dev.thiagosouto.compose.robots.exceptions

class RetryLimitException(count: Int, e: Throwable) : Exception("Reached $count", e)
