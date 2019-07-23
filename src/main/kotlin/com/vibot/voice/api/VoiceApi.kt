package com.vibot.voice.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class VoiceApi {

    @PostMapping("/buildAudio")
    fun buildAudio(request: AudioRequest): UrlResponse {
        return UrlResponse("")
    }
}