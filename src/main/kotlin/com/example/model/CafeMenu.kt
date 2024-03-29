package com.example.domain.model

import com.example.shared.CafeMenuCategory

data class CafeMenu(
    val name: String,
    val price: Int,
    val category: CafeMenuCategory,
    val image: String,
    var id: Long? = null,
)
