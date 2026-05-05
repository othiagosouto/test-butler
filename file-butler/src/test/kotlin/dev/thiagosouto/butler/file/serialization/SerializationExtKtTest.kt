package dev.thiagosouto.butler.file.serialization

import com.google.common.truth.Truth.assertThat
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.junit.Test
import java.io.FileNotFoundException

internal class SerializationExtKtTest {
    private val json = Json

    @Test
    fun `should return person from json`() {
        val content: PersonSerialization = parse(json, "person")
        assertThat(content).isEqualTo(PersonSerialization("Thiago", "Santos", 29))
    }

    @Test(expected = SerializationException::class)
    fun `should throw SerializationException when json is not valid`() {
        parse<PersonSerialization>(json, "person_invalid_format")
    }

    @Test(expected = FileNotFoundException::class)
    fun `should throw FileNotFoundException when json path does not exist`() {
        parse<PersonSerialization>(json, "perso2n")
    }

    @Test
    fun `json parseFrom should parse to expected object`() {
        val content: PersonSerialization = json.parseFrom("person")
        assertThat(content).isEqualTo(PersonSerialization("Thiago", "Santos", 29))
    }

    @Test
    fun `json parseListFrom should parse list`() {
        val content: List<PersonSerialization> = json.parseListFrom("persons")
        assertThat(content).isEqualTo(
            listOf(
                PersonSerialization("Thiago", "Santos", 29),
                PersonSerialization("Souto", "Silva", 45),
            ),
        )
    }

    @Test
    fun `json parseList should parse list`() {
        val content: List<PersonSerialization> = parseList(json, "persons")
        assertThat(content).isEqualTo(
            listOf(
                PersonSerialization("Thiago", "Santos", 29),
                PersonSerialization("Souto", "Silva", 45),
            ),
        )
    }

    @Test(expected = FileNotFoundException::class)
    fun `json parseFrom should throw FileNotFoundException when json path does not exist`() {
        json.parseFrom<PersonSerialization>("perso2n")
    }

    @Test(expected = SerializationException::class)
    fun `parseFrom should throw SerializationException when json is not valid`() {
        json.parseFrom<PersonSerialization>("person_invalid_format")
    }
}
