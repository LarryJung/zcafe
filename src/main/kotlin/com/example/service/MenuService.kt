package com.example.service

import com.example.domain.model.CafeMenu
import com.example.domain.repository.CafeMenuRepository

class MenuService(
    private val cafeMenuRepository: CafeMenuRepository
) {
    fun findAll(): List<CafeMenu> {
        return cafeMenuRepository.findAll()
    }

    fun getMenu(id: Long): CafeMenu {
        return cafeMenuRepository.read(id)
            ?: throw IllegalArgumentException("Menu not found")
    }
}