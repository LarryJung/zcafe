package com.example.config

import io.ktor.server.application.*
import org.h2.tools.Server

fun Application.configureDatabase() {
    configureH2()
}


fun Application.configureH2() {
    val h2Server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092")
    environment.monitor.subscribe(ApplicationStarted) { application ->
        h2Server.start()
        application.environment.log.info("H2 server started. ${h2Server.url}")
    }

    environment.monitor.subscribe(ApplicationStopped) { application ->
        h2Server.stop()
        application.environment.log.info("H2 server stopped. ${h2Server.url}")
    }
}