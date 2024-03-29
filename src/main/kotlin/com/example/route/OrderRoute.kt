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
        get("/orders/{orderCode}") {
            val orderCode = call.parameters["orderCode"]!!
            val order = orderService.getOrder(orderCode, call.authenticatedUser())
            call.respond(order)
        }
        put("/orders/{orderCode}/status") {
            val orderCode = call.parameters["orderCode"]!!
            val state = call.receive<OrderDto.UpdateStatusRequest>().status
            orderService.updateOrderStatus(orderCode, state, call.authenticatedUser())
            call.respond(HttpStatusCode.OK)
        }
    }
}