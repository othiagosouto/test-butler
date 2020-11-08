package com.github.othiagosouto.butler.file.moshi

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException

class MoshiExtKtTest {

    private lateinit var moshi: Moshi

    @Before
    fun setup() {
        moshi = Moshi.Builder().build()
    }

    @Test
    fun `should return person from json`() {
        val content: PersonMoshi =
            parse(moshi, "person")
        assertThat(content).isEqualTo(
            PersonMoshi(
                "Thiago",
                "Santos",
                29
            )
        )
    }

    @Test(expected = JsonDataException::class)
    fun `should throw JsonSyntaxException when json is not valid`() {
        parse<PersonMoshi>(
            moshi,
            "person_invalid_format"
        )
    }

    @Test(expected = FileNotFoundException::class)
    fun `should throw FileNotFoundException when json path doest not exist`() {
        parse<PersonMoshi>(
            moshi,
            "perso2n"
        )
    }

    @Test
    fun `moshi parseFrom should parse to expected object`() {
        val content: PersonMoshi = moshi.parseFrom("person")
        assertThat(content).isEqualTo(
            PersonMoshi(
                "Thiago",
                "Santos",
                29
            )
        )
    }

    @Test
    fun `moshi parseListFrom should parse list`() {
        val content: List<PersonMoshi> = moshi.parseListFrom("persons")
        assertThat(content).isEqualTo(
            listOf(
                PersonMoshi(
                    "Thiago",
                    "Santos",
                    29
                ), PersonMoshi(
                    "Souto",
                    "Silva",
                    45
                )
            )
        )
    }

    @Test
    fun `moshi parseList should parse list`() {
        val content: List<PersonMoshi> =
            parseList(moshi, "persons")
        assertThat(content).isEqualTo(
            listOf(
                PersonMoshi(
                    "Thiago",
                    "Santos",
                    29
                ), PersonMoshi(
                    "Souto",
                    "Silva",
                    45
                )
            )
        )
    }

    @Test(expected = FileNotFoundException::class)
    fun `moshi parseFrom should throw FileNotFoundException when json path doest not exist`() {
        moshi.parseFrom<PersonMoshi>("perso2n")
    }

    @Test(expected = JsonDataException::class)
    fun `parseFrom should throw JsonDataException when json is not valid`() {
        moshi.parseFrom<PersonMoshi>("person_invalid_format")
    }
}
