package com.example.route

import com.example.config.AuthenticatedUser
import com.example.config.authenticatedUser
import com.example.service.OrderService
import com.example.shared.dto.OrderDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.orderRoute() {
    val orderService by inject<OrderService>()

    authenticate(AuthenticatedUser.CUSTOMER_REQUIRED) {
        post("/orders") {
            val createRequest = call.receive<OrderDto.CreateRequest>()
            val code = orderService.createOrder(createRequest, call.authenticatedUser())
            call.respond(code)
        }
        put("/orders/{orderCode}/status") {
            val orderCode = call.parameters["orderCode"]!!
            val state = call.receive<OrderDto.UpdateStatusRequest>().status
            orderService.updateOrderStatus(orderCode, state, call.authenticatedUser())
            call.respond(HttpStatusCode.OK)
        }
    }

    authenticate(AuthenticatedUser.USER_REQUIRED) {
        get("/orders/{orderCode}") {
            val orderCode = call.parameters["orderCode"]!!
            val order = orderService.getOrder(orderCode, call.authenticatedUser())
            call.respond(order)
        }
    }

    authenticate(AuthenticatedUser.ADMINISTER_REQUIRED) {
        get("/orders") {
            val orders: List<OrderDto.DisplayResponse> = orderService.getOrders()
            call.respond(orders)
        }
        get("/orders/stats") {
            val stats = orderService.getOrderStats()
            call.respond(stats)
        }
    }
}