package com.example.route

import com.example.config.appMicrometerRegistry
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.monitorRoute() {
    get("/metrics") {
        call.respond(appMicrometerRegistry.scrape())
    }
}
