package com.vibot.voice.api

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.*
import org.junit.Test

class AudioIdBuilderTest {

    @Test
    fun `should return random id`() {
        val id = AudioIdBuilder().build()

        assertThat(id, `is`(notNullValue()))
    }
}