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
     * map with Relative path to json path to mock the http responses
     */
    var mapping: Map<String, String> = emptyMap()

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
     */
    fun initDispatcher() {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                println(request.path)
                val path = mapping[request.path]
                return if (path?.isImage() == true) {
                    imageResponse(path)
                } else {
                    val body = path?.openFile()
                    body?.let { MockResponse().setBody(body) }
                        ?: MockResponse().setResponseCode(
                            HTTP_BAD_REQUEST
                        )
                }
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

    companion object {
        private val IMAGES_EXTENSIONS = arrayOf(".jpeg", ".jpg")
        private const val HTTP_SUCCESS = 200
        private const val HTTP_BAD_REQUEST = 400
        private const val DEFAULT_PORT = 53863
    }
}
