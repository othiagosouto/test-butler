package dev.thiagosouto.butler.file.gson

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.junit.Test
import java.io.FileNotFoundException

internal class GsonExtKtTest {

    @Test
    fun `should return person from json`() {
        val content: Person =
            parse(Gson(), "person")
        assertThat(content).isEqualTo(
            Person(
                "Thiago",
                "Santos",
                29
            )
        )
    }

    @Test(expected = JsonSyntaxException::class)
    fun `should throw JsonSyntaxException when json is not valid`() {
        parse<Person>(
            Gson(),
            "person_invalid_format"
        )
    }

    @Test(expected = FileNotFoundException::class)
    fun `should throw FileNotFoundException when json path doest not exist`() {
        parse<Person>(
            Gson(),
            "perso2n"
        )
    }
}
