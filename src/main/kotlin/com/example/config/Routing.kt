package com.example.config

import com.example.route.menuRoute
import com.example.route.monitorRoute
import com.example.route.orderRoute
import com.example.route.userRoute
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/api") {
            userRoute()
            menuRoute()
            orderRoute()
        }

        monitorRoute()
        singlePageApplication {
            react("frontend")
        }
    }
}
