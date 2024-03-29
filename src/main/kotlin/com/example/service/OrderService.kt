package com.example.service

import com.example.config.AuthenticatedUser
import com.example.domain.model.CafeOrder
import com.example.domain.repository.CafeOrderRepository
import com.example.shared.CafeException
import com.example.shared.CafeOrderStatus
import com.example.shared.ErrorCode
import com.example.shared.dto.OrderDto
import java.time.LocalDateTime
import java.util.UUID

class OrderService(
    private val menuService: MenuService,
    private val userService: UserService,
    private val cafeOrderRepository: CafeOrderRepository,
) {
    fun createOrder(
        createRequest: OrderDto.CreateRequest,
        authenticatedUser: AuthenticatedUser,
    ): String {
        val menu = menuService.getMenu(createRequest.menuId)
        val order = CafeOrder(
            cafeUserId = authenticatedUser.userId,
            cafeMenuId = menu.id!!,
            price = menu.price,
            status = CafeOrderStatus.READY,
            orderedAt = LocalDateTime.now(),
            orderCode = "OC${UUID.randomUUID()}"
        )
        return cafeOrderRepository.create(order).orderCode
    }

    fun getOrder(
        orderCode: String,
        authenticatedUser: AuthenticatedUser,
    ): OrderDto.DisplayResponse {
        val order = getOrderByCode(orderCode)

        checkOrderOwner(authenticatedUser, order)

        return OrderDto.DisplayResponse(
            orderCode = order.orderCode,
            menuName = menuService.getMenu(order.cafeMenuId).name,
            customerName = userService.getUser(order.cafeUserId).nickname,
            price = order.price,
            status = order.status,
            orderedAt = order.orderedAt,
            id = null // 명시적 null
        )
    }

    fun updateOrderStatus(
        orderCode: String,
        status: CafeOrderStatus,
        authenticatedUser: AuthenticatedUser,
    ) {
        val order = getOrderByCode(orderCode)

        checkOrderOwner(authenticatedUser, order)
        checkCustomerAction(authenticatedUser, status)

        order.update(status)
        cafeOrderRepository.update(order)
    }

    private fun checkOrderOwner(
        authenticatedUser: AuthenticatedUser,
        order: CafeOrder,
    ) {
        if (authenticatedUser.isOnlyCustomer()) {
            if (authenticatedUser.userId != order.cafeUserId) {
                throw CafeException(ErrorCode.FORBIDDEN)
            }
        }
    }

    private fun checkCustomerAction(
        authenticatedUser: AuthenticatedUser,
        status: CafeOrderStatus,
    ) {
        if (authenticatedUser.isOnlyCustomer()) {
            if (status != CafeOrderStatus.CANCEL) {
                throw CafeException(ErrorCode.FORBIDDEN)
            }
        }
    }

    private fun getOrderByCode(orderCode: String): CafeOrder {
        return cafeOrderRepository.findByCode(orderCode)
            ?: throw CafeException(ErrorCode.ORDER_NOT_FOUND)
    }

    fun getOrders(): List<OrderDto.DisplayResponse> {
        return cafeOrderRepository.findByOrders()
    }
}
