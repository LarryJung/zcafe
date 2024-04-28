package com.example.domain.repository

import com.example.domain.CafeOrderTable
import com.example.domain.ExposedCrudRepository
import com.example.domain.model.CafeOrder
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
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
}
