package com.example.config


import com.example.config.plugin.MyCallLogging
import io.ktor.server.application.*

fun Application.configureLogging() {
//    install(CallLogging)
    install(MyCallLogging)
}
