package com.example.service

import com.example.config.AuthenticatedUser
import com.example.shared.dto.UserDto
import io.ktor.server.sessions.*

class LoginService(
    private val userService: UserService,
) {
    fun login(
        cafeUserLoginRequest: UserDto.LoginRequest,
        currentSession: CurrentSession,
    ) {
        checkNoSession(currentSession)

        val cafeUser = userService.getCafeUser(
            nickname = cafeUserLoginRequest.nickname,
            plainPassword = cafeUserLoginRequest.plainPassword
        )
        currentSession.set(AuthenticatedUser(cafeUser.id!!, cafeUser.roles))
    }

    fun signup(
        cafeUserLoginRequest: UserDto.LoginRequest,
        currentSession: CurrentSession,
    ) {
        checkNoSession(currentSession)

        val cafeUser = userService.createCustomer(
            nickname = cafeUserLoginRequest.nickname,
            plainPassword = cafeUserLoginRequest.plainPassword
        )
        currentSession.set(AuthenticatedUser(cafeUser.id!!, cafeUser.roles))
    }

    fun logout(currentSession: CurrentSession) {
        currentSession.clear(AuthenticatedUser.SESSION_NAME)
    }

    private fun checkNoSession(currentSession: CurrentSession) {
        val authenticatedUser = currentSession.get<AuthenticatedUser>()
        if (authenticatedUser != null) {
            throw RuntimeException()
        }
    }
}