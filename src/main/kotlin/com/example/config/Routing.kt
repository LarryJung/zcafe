package com.example.config

import com.example.domain.repository.CafeMenuRepository
import com.example.shared.CafeOrderStatus
import com.example.shared.dto.OrderDto
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDateTime


fun Application.configureRouting(cafeMenuRepository: CafeMenuRepository) {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        route("/api") {
            get("/menus") {
                val list = cafeMenuRepository.findAll()
                call.respond(list)
            }
            post("/orders") {
                val request = call.receive<OrderDto.CreateRequest>()
                val selectedMenu = cafeMenuRepository.read(request.menuId)!!
                val order = OrderDto.DisplayResponse(
                    orderCode = "ordercode1",
                    menuName = selectedMenu.name,
                    customerName = "홍길동",
                    price = selectedMenu.price,
                    status = CafeOrderStatus.READY,
                    orderedAt = LocalDateTime.now(),
                    id = 1
                )
                call.respond(order)
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
    }
}
