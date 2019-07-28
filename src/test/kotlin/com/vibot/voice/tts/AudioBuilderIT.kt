package com.vibot.voice.tts

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Test
import java.io.File

private const val AUDIO_ID = "temporal_id"

class AudioBuilderIT {

    private val builder = AudioBuilder()

    @After
    fun tearDown() {
        audioFile().delete()
    }

    @Test
    fun `should build audio wav`() {
        builder.build(AUDIO_ID, "any text")

        assertThat(audioFile().exists(), `is`(true))
    }

    private fun audioFile() = File("audios/$AUDIO_ID.wav")
}