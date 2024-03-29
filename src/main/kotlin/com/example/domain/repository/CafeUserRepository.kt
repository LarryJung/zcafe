package com.example.domain.repository

import com.example.domain.CafeUserTable
import com.example.domain.ExposedCrudRepository
import com.example.domain.model.CafeUser
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement

class CafeUserRepository(
    override val table: CafeUserTable,
) : ExposedCrudRepository<CafeUserTable, CafeUser> {

    override fun toRow(domain: CafeUser): CafeUserTable.(InsertStatement<EntityID<Long>>) -> Unit = {
        if (domain.id != null) {
            it[id] = domain.id!!
        }
        it[nickname] = domain.nickname
        it[password] = domain.password
        it[roles] = domain.roles
    }

    override fun toDomain(row: ResultRow): CafeUser {
        return CafeUser(
            nickname = row[CafeUserTable.nickname],
            password = row[CafeUserTable.password],
            roles = row[CafeUserTable.roles],
        ).apply {
            id = row[CafeUserTable.id].value
        }
    }

    override fun updateRow(domain: CafeUser): CafeUserTable.(UpdateStatement) -> Unit = {
        it[nickname] = domain.nickname
        it[password] = domain.password
        it[roles] = domain.roles
    }

    fun findByNickname(nickname: String): CafeUser? = dbQuery {
        table.selectAll().where { table.nickname eq nickname }
            .map { toDomain(it) }
            .singleOrNull()
    }
}
