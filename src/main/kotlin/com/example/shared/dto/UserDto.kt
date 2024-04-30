package com.example.shared.dto


import kotlinx.serialization.Serializable

class UserDto {
    @Serializable
    data class LoginRequest(
        val nickname: String,
        val plainPassword: String,
    )
}
