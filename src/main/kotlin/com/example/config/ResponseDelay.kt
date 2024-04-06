package com.example.config

import io.ktor.server.application.*

val ResponseDelayPlugin = createApplicationPlugin(name = "ResponseDelayPlugin") {
    onCall {
        Thread.sleep(500)
    }
}