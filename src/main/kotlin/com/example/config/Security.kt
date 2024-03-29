package com.example.config

import com.example.shared.CafeUserRole
import com.example.config.AuthenticatedUser.Companion.CUSTOMER_REQUIRED
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*

fun Application.configureSecurity() {
    install(Authentication) {
        session<AuthenticatedUser>(CUSTOMER_REQUIRED) {
            validate { session: AuthenticatedUser ->
                session.takeIf { it.userRoles.contains(CafeUserRole.CUSTOMER) }
            }
            challenge {
                call.respond(HttpStatusCode.Forbidden, "only for customer");
            }
        }
    }
}

fun ApplicationCall.authenticatedUser(): AuthenticatedUser = authentication.principal<AuthenticatedUser>()!!