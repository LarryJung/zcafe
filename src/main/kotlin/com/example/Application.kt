package com.example

import com.example.config.configureDatabase
import com.example.config.configureDependencyInjection
import com.example.config.configureErrorHandling
import com.example.config.configureHttp
import com.example.config.configureLogging
import com.example.config.configureRouting
import com.example.config.configureSecurity
import com.example.config.configureSerialization
import com.example.config.configureSession
import com.example.shared.applicationEnv
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    this.log.info("current env: ${applicationEnv()}")

    configureDatabase()
    configureDependencyInjection()
    configureHttp()
    configureSession()
    configureSecurity()
    configureSerialization()
    configureRouting()
    configureErrorHandling()
    configureLogging()
}
