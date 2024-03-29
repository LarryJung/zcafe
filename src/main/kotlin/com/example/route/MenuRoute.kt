package com.example.route

import com.example.service.MenuService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.menuRoute() {
    val menuService by inject<MenuService>()

    get("/menus") {
        val menu = menuService.findAll()
        call.respond(menu)
    }
}