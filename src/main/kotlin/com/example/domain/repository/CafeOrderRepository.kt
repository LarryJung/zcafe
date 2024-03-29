package com.example.domain.repository

import com.example.domain.CafeOrderTable
import com.example.domain.ExposedCrudRepository
import com.example.domain.model.CafeOrder
import com.example.shared.dto.OrderDto
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement

class CafeOrderRepository(
    override val table: CafeOrderTable,
) : ExposedCrudRepository<CafeOrderTable, CafeOrder> {
    override fun toRow(domain: CafeOrder): CafeOrderTable.(InsertStatement<EntityID<Long>>) -> Unit = {
        if (domain.id != null) {
            it[id] = domain.id!!
        }
        it[orderCode] = domain.orderCode
        it[cafeUserId] = domain.cafeUserId
        it[cafeMenuId] = domain.cafeMenuId
        it[price] = domain.price
        it[status] = domain.status
        it[orderedAt] = domain.orderedAt
    }

    override fun toDomain(row: ResultRow): CafeOrder {
        return CafeOrder(
            orderCode = row[CafeOrderTable.orderCode],
            cafeMenuId = row[CafeOrderTable.cafeMenuId].value,
            cafeUserId = row[CafeOrderTable.cafeUserId].value,
            price = row[CafeOrderTable.price],
            status = row[CafeOrderTable.status],
            orderedAt = row[CafeOrderTable.orderedAt],
        ).apply {
            id = row[CafeOrderTable.id].value
        }
    }

    override fun updateRow(domain: CafeOrder): CafeOrderTable.(UpdateStatement) -> Unit = {
        it[orderCode] = domain.orderCode
        it[cafeUserId] = domain.cafeUserId
        it[cafeMenuId] = domain.cafeMenuId
        it[price] = domain.price
        it[status] = domain.status
        it[orderedAt] = domain.orderedAt
    }

    fun findByCode(orderCode: String): CafeOrder? = dbQuery {
        val query = table.selectAll().where { table.orderCode.eq(orderCode) }
        query
            .map(::toDomain)
            .firstOrNull()
    }

    /**
     * select o.order_code,
     *        o.price,
     *        o.status,
     *        o.ordered_at,
     *        m.menu_name,
     *        u.nickname
     * from cafe_order o
     *          inner join cafe_user u on u.id = o.cafe_user_id
     *          inner join cafe_menu m on m.id = o.cafe_menu_id
     * order by o.id desc;
     */
    fun findByOrders(): List<OrderDto.DisplayResponse> {
        TODO()
    }
}
