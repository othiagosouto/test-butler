package dev.thiagosouto.webserver.rules

import dev.thiagosouto.webserver.TestWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class WebServerTestRule(private val responses: Map<String, TestWebServer.Response>) :
    TestWatcher() {
    private val testWebServer = TestWebServer()

    override fun starting(description: Description?) {
        testWebServer.init(responses)
        testWebServer.start()
        super.starting(description)
    }

    override fun finished(description: Description?) {
        testWebServer.stop()
        super.starting(description)
    }
}
