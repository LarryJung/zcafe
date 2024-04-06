package com.example

import com.example.config.configureDatabase
import com.example.config.configureRouting
import com.example.config.configureSerialization
import com.example.domain.CafeMenuTable
import com.example.domain.repository.CafeMenuRepository
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureDatabase()
    configureSerialization()

    val cafeMenuRepository = CafeMenuRepository(CafeMenuTable)
    configureRouting(cafeMenuRepository)
}
