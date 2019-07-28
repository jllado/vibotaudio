package com.vibot.voice.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.io.File
import java.nio.file.Files


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class VoiceApiIT {

    @Autowired
    private lateinit var mvc: MockMvc

    @After
    fun tearDown() {
        deleteAudios()
    }

    @Test
    fun `send text should return audio url`() {
        val urlResponseResult = mvc.perform(post("/buildAudio").contentType(MediaType.APPLICATION_JSON_UTF8).content("{\"text\": \"any text\"}"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.url", `is`(notNullValue()))).andReturn()
        val urlResponse = ObjectMapper().registerModule(KotlinModule()).readValue(urlResponseResult.response.contentAsString, UrlResponse::class.java)
        mvc.perform(get(urlResponse.url).contentType(MediaType.parseMediaType("audio/x-wav"))).andExpect(status().isOk)
    }

    private fun deleteAudios() {
        val audiosPath = File("audios").toPath()
        Files.walk(audiosPath)
                .sorted(Comparator.reverseOrder())
                .map { it.toFile() }
                .forEach { it.delete() }
    }
}