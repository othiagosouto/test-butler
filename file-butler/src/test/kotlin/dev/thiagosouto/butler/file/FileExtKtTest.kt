package dev.thiagosouto.butler.file

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.io.FileNotFoundException

class FileExtKtTest {

    @Test
    fun `should return file content as string`() {
        val content: String = readFile("file.txt")
        assertThat(content).isEqualTo("Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
    }

    @Test(expected = FileNotFoundException::class)
    fun `should throw exception when file not exist`() {
        readFile("file.tx22t")
    }
}

