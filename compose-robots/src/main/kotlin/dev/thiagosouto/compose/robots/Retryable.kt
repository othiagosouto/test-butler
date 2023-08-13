package dev.thiagosouto.compose.robots

import androidx.compose.ui.test.junit4.ComposeTestRule

/**
 * Robot that specifies retry behavior for when an assertion fails
 */
interface Retryable {

    /**
     * Re-tries [func] using [retryConfig] parameters
     */
    fun retry(retryConfig: RetryConfig, func: ComposeTestRule.() -> Unit)


    /**
     * Re-tries [func] using [retryConfig] parameters with a delay provided by [delay], and executes [errorHandling] when fails
     */
    fun retryWithDelay(
        retryConfig: RetryConfig = RetryConfig(),
        delay: Long = 50L,
        errorHandling: ComposeTestRule.() -> Unit = { },
        func: ComposeTestRule.() -> Unit
    )

    /**
     * Configuration necessary to retry assertion
     *
     * @property limit is the number of times that the failed code can retry
     * @property count is the current execution number
     */
    data class RetryConfig(val limit: Int = 0, val count: Int = 5) {
        fun increment() = RetryConfig(limit = this.limit, count = count + 1)
    }
}
