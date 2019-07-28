package com.vibot.voice.tts

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.File
import java.io.BufferedReader
import java.io.InputStreamReader


@Service
class AudioBuilder {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(AudioBuilder::class.java)
    }

    fun build(id: String, text: String) {
        val directory = File("audios")
        directory.mkdir()
        val processBuilder = ProcessBuilder()
        processBuilder.redirectErrorStream(true)
        processBuilder.command("pico2wave", "-l", "es-ES", "-w", "$id.wav", text)
        processBuilder.directory(directory)
        val process = processBuilder.start()
        process.printLog()
        process.waitFor()
    }

    private fun Process.printLog() {
        val reader = BufferedReader(InputStreamReader(this.inputStream))
        var line = reader.readLine()
        while (line != null) {
            LOGGER.info(line)
            line = reader.readLine()
        }
    }
}

