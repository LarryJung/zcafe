package com.example.config

import com.example.shared.CafeException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*


fun Application.configureErrorHandling() {
    install(StatusPages) {
        exception<CafeException> { call, cause ->
            call.respond(cause.errorCode.httpStatusCode, cause.message ?: "Bad Request")
        }
        exception<Throwable> { call, cause ->
            call.respondText(
                status = HttpStatusCode.InternalServerError,
                text = "500: $cause"
            )
        }
    }
}