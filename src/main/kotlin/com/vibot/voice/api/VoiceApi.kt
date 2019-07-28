package com.vibot.voice.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.awt.PageAttributes
import java.io.File
import java.io.FileInputStream

@RestController
class VoiceApi @Autowired constructor(
        private val service: VoiceService
){

    @PostMapping("/buildAudio")
    fun buildAudio(@RequestBody request: AudioRequest): UrlResponse {
        return service.buildAudio(request)
    }

    @GetMapping(value = ["/audio/{id}"], produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    @ResponseBody
    fun getAudio(@PathVariable("id") id: String): ResponseEntity<Resource> {
        val file = File("audios/$id.wav")
        val resource = InputStreamResource(FileInputStream(file))
        return ResponseEntity.ok().body(resource)
    }
}