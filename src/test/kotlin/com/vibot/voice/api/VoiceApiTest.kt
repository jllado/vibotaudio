package com.vibot.voice.api

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VoiceApiTest {

    @Mock
    private lateinit var service: VoiceService

    @InjectMocks
    private lateinit var api: VoiceApi

    @Test
    fun `given text should return audio url`() {
        val request = AudioRequest("any test")
        val urlResponse = UrlResponse("http://www.audio.url")
        doReturn(urlResponse).`when`(service).buildAudio(request)

        assertThat(api.buildAudio(request), `is`(urlResponse))
    }
}
