package com.github.othiagosouto.file_butler

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.junit.Test
import java.io.FileNotFoundException

class GsonExtKtTest {

    @Test
    fun `should return person from json`() {
        val content: Person = parse(Gson(), "person")
        assertThat(content).isEqualTo(Person("Thiago", "Santos", 29))
    }

    @Test(expected = JsonSyntaxException::class)
    fun `should throw JsonSyntaxException when json is not valid`() {
        parse<Person>(Gson(), "person_invalid_format")
    }

    @Test(expected = FileNotFoundException::class)
    fun `should throw FileNotFoundException when json path doest not exist`() {
        parse<Person>(Gson(), "perso2n")
    }

    @Test
    fun `gson parseFrom should parse to expected object`() {
        val content: Person = Gson().parseFrom("person")
        assertThat(content).isEqualTo(Person("Thiago", "Santos", 29))
    }

    @Test(expected = FileNotFoundException::class)
    fun `gson parseFrom should throw FileNotFoundException when json path doest not exist`() {
        Gson().parseFrom<Person>("perso2n")
    }

    @Test(expected = JsonSyntaxException::class)
    fun `parseFrom should throw JsonSyntaxException when json is not valid`() {
        Gson().parseFrom<Person>("person_invalid_format")
    }
}

