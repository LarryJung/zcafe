package com.example.domain

import com.example.shared.CafeMenuCategory
import com.example.shared.CafeOrderStatus
import com.example.shared.CafeUserRole
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object CafeMenuTable : LongIdTable(name = "cafe_menu") {
    val name = varchar("menu_name", length = 50)
    val price = integer("price")
    val category = enumerationByName("category", 10, CafeMenuCategory::class)
    val image = text("image")
}

object CafeUserTable : LongIdTable(name = "cafe_user") {
    val nickname = varchar("nickname", length = 50)
    val password = varchar("password", length = 100)
    val roles = enumList("roles", CafeUserRole::class.java, 20)
}

object CafeOrderTable : LongIdTable(name = "cafe_order") {
    val orderCode = varchar("order_code", length = 50)
    val cafeUserId = reference("cafe_user_id", CafeUserTable)
    val cafeMenuId = reference("cafe_menu_id", CafeMenuTable)
    val price = integer("price")
    val status = enumerationByName("status", 10, CafeOrderStatus::class)
    val orderedAt = datetime("ordered_at")
}