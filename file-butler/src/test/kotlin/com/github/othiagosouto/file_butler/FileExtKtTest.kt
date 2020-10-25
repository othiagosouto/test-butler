package com.github.othiagosouto.file_butler;

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.junit.Test
import java.io.FileNotFoundException

class FileExtKtTest {

    @Test
    fun `should return string from file`() {
        val content: String = readFile("file.txt")
        assertThat(content).isEqualTo("Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
    }

    @Test(expected = FileNotFoundException::class)
    fun `should throw exception when file not exist`() {
        readFile("file.tx22t")
    }

    @Test
    fun `should return person from json`() {
        val content: Person = parse(Gson(), "person.json")
        assertThat(content).isEqualTo(Person("Thiago", "Santos", 29))
    }

    @Test
    fun `gson parseFrom should parse to expected object`() {
        val content: Person = Gson().parseFrom("person.json")
        assertThat(content).isEqualTo(Person("Thiago", "Santos", 29))
    }
}

data class Person(val name: String, @SerializedName("last_name") val lastName: String, val age: Int)