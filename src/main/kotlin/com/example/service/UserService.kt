package com.example.service

import com.example.domain.model.CafeUser
import com.example.domain.repository.CafeUserRepository
import com.example.shared.BCryptPasswordEncoder
import com.example.shared.CafeException
import com.example.shared.CafeUserRole
import com.example.shared.ErrorCode

class UserService(
    private val cafeUserRepository: CafeUserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
) {

    fun createCustomer(nickname: String, plainPassword: String): CafeUser {
        val existedUser = cafeUserRepository.findByNickname(nickname)
        if (existedUser != null) {
            throw CafeException(ErrorCode.USER_ALREADY_EXISTS)
        }

        val hashedPassword = passwordEncoder.encode(plainPassword)
        val cafeUser = CafeUser(
            nickname = nickname,
            password = hashedPassword,
            roles = listOf(CafeUserRole.CUSTOMER)
        )
        return cafeUserRepository.create(cafeUser)
    }

    fun getCafeUser(nickname: String, plainPassword: String): CafeUser {
        val cafeUser = cafeUserRepository.findByNickname(nickname)
            ?: throw CafeException(ErrorCode.USER_NOT_FOUND)

        if (!passwordEncoder.matches(
                password = plainPassword,
                hashed = cafeUser.password
            )
        ) {
            throw CafeException(ErrorCode.PASSWORD_INCORRECT)
        }
        return cafeUser
    }
}