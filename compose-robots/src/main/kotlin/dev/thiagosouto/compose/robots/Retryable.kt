package dev.thiagosouto.compose.robots

import androidx.compose.ui.test.junit4.ComposeTestRule

interface Retryable {

    fun retry(retryConfig: RetryConfig, func: ComposeTestRule.() -> Unit)

    fun retryWithDelay(
        retryConfig: RetryConfig = RetryConfig(),
        delay: Long = 50L,
        errorHandling: ComposeTestRule.() -> Unit = { },
        func: ComposeTestRule.() -> Unit
    )

    data class RetryConfig(val limit: Int = 0, val count: Int = 5) {
        fun increment() = RetryConfig(limit = this.limit, count = count + 1)
    }
}
