package com.example.config

import com.example.domain.CafeMenuTable
import com.example.domain.CafeOrderTable
import com.example.domain.CafeUserTable
import com.example.domain.repository.CafeMenuRepository
import com.example.domain.repository.CafeOrderRepository
import com.example.domain.repository.CafeUserRepository
import com.example.service.MenuService
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

val appModule = module {
    single { CafeMenuRepository(CafeMenuTable) }
    single { CafeUserRepository(CafeUserTable) }
    single { CafeOrderRepository(CafeOrderTable) }

    single { MenuService(get()) }
}

fun Application.configureDependencyInjection() {
    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }
}
