package com.vibot.voice.tts

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File


private const val chunkSize = 30000

@Service
class AudioBuilder @Autowired constructor(
        private val commandRunner: CommandRunner
){

    fun build(id: String, text: String) {
        val directory = File("audios")
        directory.mkdir()
        if (text.length <= chunkSize) {
            text.toAudio(id, directory)
            return
        }
        val textChunks = text.chunked(chunkSize)
        textChunks.forEachIndexed { index, textChunk ->  textChunk.toAudio(buildChunkId(id, index), directory)}
        mergeChunks(textChunks.size, id, directory)
    }

    private fun mergeChunks(size: Int, id: String, directory: File) {
        val command = mutableListOf("sox")
        for (index in 0 until size) {
            command.add("${buildChunkId(id, index)}.wav")
        }
        command.add("$id.wav")
        commandRunner.run(directory, *command.toTypedArray())
    }

    private fun String.toAudio(id: String, directory: File) {
        val text = this
        val output = commandRunner.run(directory, "pico2wave", "-l", "es-ES", "-w", "$id.wav", text)
        if (output.contains("invalid argument supplied")) {
            throw IllegalArgumentException("Build audio failed")
        }
    }

    private fun buildChunkId(id: String, index: Int) = "$id-$index"
}

