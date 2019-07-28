package com.vibot.voice.api

import com.vibot.voice.tts.AudioBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VoiceService @Autowired constructor(
        private val builder: AudioBuilder,
        private val idBuilder: AudioIdBuilder
){

    fun buildAudio(request: AudioRequest): UrlResponse {
        val id = idBuilder.build()
        builder.build(id, request.text)
        return UrlResponse("/audio/$id")
    }
}
