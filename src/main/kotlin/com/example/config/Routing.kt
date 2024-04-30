package com.example.config

import com.example.service.LoginService
import com.example.service.MenuService
import com.example.shared.CafeOrderStatus
import com.example.shared.dto.OrderDto
import com.example.shared.dto.UserDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import org.koin.ktor.ext.inject
import java.time.LocalDateTime


fun Application.configureRouting() {
    val menuService by inject<MenuService>()
    val loginService by inject<LoginService>()

    routing {
        route("/api") {
            get("/menus") {
                val list = menuService.findAll()
                call.respond(list)
            }
            authenticate(AuthenticatedUser.CUSTOMER_REQUIRED) {
                post("/orders") {
                    val request = call.receive<OrderDto.CreateRequest>()
                    val selectedMenu = menuService.getMenu(request.menuId)
                    val order = OrderDto.DisplayResponse(
                        orderCode = "ordercode1",
                        menuName = selectedMenu.name,
                        customerName = "홍길동",
                        price = selectedMenu.price,
                        status = CafeOrderStatus.READY,
                        orderedAt = LocalDateTime.now(),
                        id = 1
                    )
                    call.respond(order.orderCode)
                }
                get("/orders/{orderCode}") {
                    val orderCode = call.parameters["orderCode"]!!
                    val order = OrderDto.DisplayResponse(
                        orderCode = orderCode,
                        menuName = "아이스라떼",
                        customerName = "홍길동",
                        price = 1000,
                        status = CafeOrderStatus.READY,
                        orderedAt = LocalDateTime.now(),
                        id = 1
                    )
                    call.respond(order)
                }
            }

            get("/me") {
                val user = call.sessions.get<AuthenticatedUser>() ?: AuthenticatedUser.none()
                call.respond(user)
            }
            post("/login") {
                val user = call.receive<UserDto.LoginRequest>()
                loginService.login(user, call.sessions)
                call.respond(HttpStatusCode.OK)
            }

            post("/signup") {
                val request = call.receive<UserDto.LoginRequest>()
                loginService.signup(request, call.sessions)
                call.respond(HttpStatusCode.OK)
            }

            post("/logout") {
                loginService.logout(call.sessions)
                call.respond(HttpStatusCode.OK)
            }
        }

        singlePageApplication {
            react("frontend")
        }
    }
}
