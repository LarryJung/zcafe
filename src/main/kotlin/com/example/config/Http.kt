package com.example.config

import io.ktor.server.application.*
import io.ktor.server.plugins.doublereceive.*

fun Application.configureHttp() {
    install(DoubleReceive)
    install(ResponseDelayPlugin)
}

