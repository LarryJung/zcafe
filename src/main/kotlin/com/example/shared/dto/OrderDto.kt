package com.example.shared.dto

import com.example.shared.CafeOrderStatus
import java.time.LocalDateTime

class OrderDto {
    data class CreateRequest(val menuId: Long)

    data class DisplayResponse(
        val orderCode: String,
        val menuName: String,
        val customerName: String,
        val price: Int,
        var status: CafeOrderStatus,
        val orderedAt: LocalDateTime,
        var id: Long? = null,
    )
}