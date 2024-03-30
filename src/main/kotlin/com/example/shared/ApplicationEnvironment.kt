package com.example.shared

import io.ktor.server.application.*
import java.util.*


enum class ApplicationEnv {
    LOCAL, DEV, PROD
}

fun Application.applicationEnv(): ApplicationEnv =
    getPropertyString("ktor.environment")!!.let { ApplicationEnv.valueOf(it.uppercase(Locale.getDefault())) }


fun Application.getPropertyString(path: String, default: String? = null): String? =
    environment.config.propertyOrNull(path)?.getString() ?: default

fun Application.getPropertyBoolean(path: String, default: Boolean): Boolean =
    environment.config.propertyOrNull(path)?.getString()?.toBoolean() ?: default

fun Application.getPropertyList(path: String): List<String> =
    environment.config.propertyOrNull(path)?.getList() ?: emptyList()