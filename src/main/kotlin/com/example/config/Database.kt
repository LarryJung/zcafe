package com.example.config

import com.example.domain.CafeMenuTable
import com.example.domain.CafeOrderTable
import com.example.domain.CafeUserTable
import com.example.domain.model.CafeOrder
import com.example.shared.CafeOrderStatus
import com.example.shared.dummyMenuQueryList
import com.example.shared.dummyUserQueryList
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.h2.tools.Server
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.util.*
import javax.sql.DataSource
import kotlin.random.Random

fun Application.configureDatabase() {
    configureH2()
    connectDatabase()
    initData()
}


fun Application.configureH2() {
    val h2Server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092")
    environment.monitor.subscribe(ApplicationStarted) { application ->
        h2Server.start()
        application.environment.log.info("H2 server started. ${h2Server.url}")
    }

    environment.monitor.subscribe(ApplicationStopped) { application ->
        h2Server.stop()
        application.environment.log.info("H2 server stopped. ${h2Server.url}")
    }
}


private fun connectDatabase() {
    val config =
        HikariConfig().apply {
            jdbcUrl = "jdbc:h2:mem:cafedb"
            driverClassName = "org.h2.Driver"
            validate()
        }

    val dataSource: DataSource = HikariDataSource(config)
    Database.connect(dataSource)
}

private fun initData() {
    transaction {
        addLogger(StdOutSqlLogger)

        SchemaUtils.create(
            CafeMenuTable,
            CafeUserTable,
            CafeOrderTable
        )

        execInBatch(dummyUserQueryList)
        execInBatch(dummyMenuQueryList)
        batchInsertOrder()
    }
}

private fun batchInsertOrder(): List<ResultRow> {
    val menuPairs = CafeMenuTable.selectAll()
        .toList()
        .map { it[CafeMenuTable.id].value to it[CafeMenuTable.price] }

    // batch insert for dsl
    val iterator =
        (1..300).map { id ->
            val (menuId, price) = menuPairs.random()
            CafeOrder(
                orderCode = "OC${UUID.randomUUID()}",
                cafeUserId = 1L,
                cafeMenuId = menuId,
                price = price,
                status = CafeOrderStatus.READY,
                orderedAt = LocalDateTime.now().minusDays(Random.nextLong(10))
            )
        }

    return CafeOrderTable.batchInsert(
        iterator,
        shouldReturnGeneratedValues = false,
        body = {
            this[CafeOrderTable.orderCode] = it.orderCode
            this[CafeOrderTable.cafeMenuId] = it.cafeMenuId
            this[CafeOrderTable.cafeUserId] = it.cafeUserId
            this[CafeOrderTable.price] = it.price
            this[CafeOrderTable.status] = it.status
            this[CafeOrderTable.orderedAt] = it.orderedAt
        }
    )
}