package com.example.domain.model


import com.example.shared.CafeUserRole
import com.example.domain.BaseModel
import kotlinx.serialization.Serializable

@Serializable
data class CafeUser(
    val nickname: String,
    val password: String,
    val roles: List<CafeUserRole>,
    override var id: Long? = null,
) : BaseModel
