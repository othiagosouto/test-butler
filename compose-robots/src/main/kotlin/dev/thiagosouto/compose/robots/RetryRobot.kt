package dev.thiagosouto.compose.robots

import androidx.compose.ui.test.ComposeTimeoutException
import androidx.compose.ui.test.junit4.ComposeTestRule
import dev.thiagosouto.compose.robots.exceptions.RetryLimitException

internal class RetryRobot(private val rule: ComposeTestRule) : Retryable {
    override fun retry(retryConfig: Retryable.RetryConfig, func: ComposeTestRule.() -> Unit) {
        try {
            rule.func()
        } catch (e: ComposeTimeoutException) {
            retryException(e, retryConfig, func)
        } catch (e: AssertionError) {
            retryException(e, retryConfig, func)
        }
    }

    private fun retryException(
        e: Throwable,
        retryConfig: Retryable.RetryConfig = Retryable.RetryConfig(),
        func: ComposeTestRule.() -> Unit
    ) {
        if (retryConfig.count == retryConfig.limit) {
            throw e
        }
        retry(retryConfig.increment(), func)
    }

    override fun retryWithDelay(
        retryConfig: Retryable.RetryConfig,
        delay: Long,
        errorHandling: ComposeTestRule.() -> Unit,
        func: ComposeTestRule.() -> Unit,
    ) {
        try {
            rule.func()
        } catch (e: ComposeTimeoutException) {
            errorHandling(rule)
            retryException(delay, e, retryConfig.increment(), func, errorHandling)
        } catch (e: AssertionError) {
            errorHandling(rule)
            retryException(delay, e, retryConfig.increment(), func, errorHandling)
        }
    }

    private fun retryException(
        delay: Long,
        e: Throwable,
        retryConfig: Retryable.RetryConfig = Retryable.RetryConfig(),
        func: ComposeTestRule.() -> Unit,
        errorHandling: ComposeTestRule.() -> Unit
    ) {
        if (retryConfig.count == retryConfig.limit) {
            throw RetryLimitException(retryConfig.count, e)
        }

        if (delay != 0L) {
            Thread.sleep(delay)
        }
        retryWithDelay(retryConfig.increment(), delay, func, errorHandling)
    }
}
