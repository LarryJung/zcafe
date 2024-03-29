package com.example.config.plugin

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.logging.*
import org.slf4j.event.Level


internal val LOGGER = KtorSimpleLogger("[CallLogging]")
val MyCallLogging =
    createRouteScopedPlugin("MyCallLogging") {
        onCallRespond { call, body ->
            if (!call.request.uri.startsWith("/api")) return@onCallRespond

            LOGGER
                .atLevel(getLevel(call))
                .log(format(call, body))
        }
    }

fun getLevel(call: ApplicationCall): Level =
    if (call.response.status()?.isSuccess() ?: true) Level.INFO else Level.ERROR

suspend fun format(call: ApplicationCall, body: Any): String {
    val method = call.request.httpMethod.value
    val uri = call.request.uri
    val requestBody = call.receiveText()
        .replace("\r\n", "")
        .replace("\n", "")
        .replace(" ", "")

    val bodyStr = if (body is TextContent) {
        "Response: ${body.text}"
    } else {
        ""
    }

    val request = "Request: $method $uri, $requestBody"
    val status = "${call.response.status()?.value ?: ""}"
    val log = listOf(request, status, bodyStr).filter { it.isNotBlank() }.joinToString("\n")

    return "\n" + log
}