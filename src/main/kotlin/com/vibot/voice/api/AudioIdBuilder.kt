package com.vibot.voice.api

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service

@Service
class AudioIdBuilder {

    fun build(): String = RandomStringUtils.randomAlphabetic(8)
}
