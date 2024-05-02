package com.example.route

import com.example.config.AuthenticatedUser
import com.example.domain.model.CafeMenu
import com.example.service.MenuService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.menuRoute() {
    val menuService by inject<MenuService>()

    get("/menus") {
        val menu = menuService.findAll()
        call.respond(menu)
    }

    authenticate(AuthenticatedUser.ADMINISTER_REQUIRED) {
        post("/menus") {
            val menu = call.receive<CafeMenu>()
            val createdMenu = menuService.createMenu(menu)
            call.respond(createdMenu)
        }

        put("/menus") {
            val menu = call.receive<CafeMenu>()
            val updatedMenu = menuService.updateMenu(menu)
            call.respond(updatedMenu)
        }

        delete("/menus/{id}") {
            val id = call.parameters["id"]?.toLong()!!
            menuService.deleteMenu(id)
            call.respond(HttpStatusCode.OK)
        }
    }
}
