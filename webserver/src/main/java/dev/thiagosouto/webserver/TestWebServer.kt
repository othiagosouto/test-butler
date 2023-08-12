package dev.thiagosouto.webserver

import dev.thiagosouto.butler.file.readFile
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.Buffer

/**
 * Webserver for supporting tests with http calls
 */
class TestWebServer {
    private val server = MockWebServer()

    /**
     * Start the webserver
     */
    fun start(port: Int = DEFAULT_PORT) {
        server.start(port)
    }

    /**
     * Stop the webserver
     */
    fun stop() {
        server.close()
    }

    /**
     * initialize the dispatcher for the webserver
     *
     * @param responses is a map with the configuration for the dispatcher
     */
    fun init(responses: Map<String, Response>) {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                val response = responses[request.path]
                return if (response != null) {
                    if (response.path.isImage()) {
                        imageResponse(response.path)
                    } else {
                        val body = response.path.openFile()
                        body.let { MockResponse().setBody(body).setResponseCode(response.httpCode) }
                    }
                } else { throw UrlNotMockedException(request.path!!) }
            }
        }
    }

    private fun String.isImage() = IMAGES_EXTENSIONS.any { this.contains(it) }

    private fun imageResponse(path: String): MockResponse {
        val responseBody = path.getBinaryFileAsBuffer()
        return MockResponse().setResponseCode(HTTP_SUCCESS).addHeader(path.getContentType())
            .setBody(responseBody)
    }

    private fun String.getBinaryFileAsBuffer(): Buffer {
        val file = TestWebServer::class.java.classLoader!!.getResource(this)
        val fileData: ByteArray = file.readBytes()
        val buf = Buffer()
        buf.write(fileData)
        return buf
    }

    /**
     * returns the webserver url
     *
     * @param path is the relative path to merge with the server host
     */
    fun url(path: String = "") = server.url(path).toString()

    private fun String.openFile(): String {
        return readFile(TestWebServer::class.java.classLoader, this)
    }

    private fun String.getContentType(): String {
        return when {
            this.contains(".jpg") -> "Content-Type:image/jpg"
            else -> "Content-Type:image/jpeg"
        }
    }

    /**
     * Response from request
     *
     * @property path is the relative for the url
     * @property httpCode is the expected http code to be return as result for the url
     */
    data class Response(val path: String, val httpCode: Int = 200)

    private companion object {
        val IMAGES_EXTENSIONS = arrayOf(".jpeg", ".jpg")
        const val HTTP_SUCCESS = 200
        const val DEFAULT_PORT = 53863
    }
}
