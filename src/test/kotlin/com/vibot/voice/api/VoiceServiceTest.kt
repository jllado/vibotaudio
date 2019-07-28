package com.vibot.voice.api

import com.vibot.voice.tts.AudioBuilder
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VoiceServiceTest {

    @Mock
    private lateinit var audioBuilder: AudioBuilder
    @Mock
    private lateinit var idBuilder: AudioIdBuilder

    @InjectMocks
    private lateinit var service: VoiceService

    @Test
    fun `given text should build audio with that text`() {
        val id = "wavid"
        val text = "any text"
        val request = AudioRequest(text)
        doReturn(id).`when`(idBuilder).build()

        service.buildAudio(request)

        verify(audioBuilder).build(id, text)
    }

    @Test
    fun `given text should return audio url`() {
        val id = "wavid"
        val request = AudioRequest("any text")
        doReturn(id).`when`(idBuilder).build()

        assertThat(service.buildAudio(request), `is`(UrlResponse("/audio/$id")))
    }
}