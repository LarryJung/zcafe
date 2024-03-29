package com.example.domain

interface CrudRepository<DOMAIN : BaseModel> {
    fun create(domain: DOMAIN): DOMAIN
    fun findAll(): List<DOMAIN>
    fun read(id: Long): DOMAIN?
    fun update(domain: DOMAIN): DOMAIN
    fun delete(domain: DOMAIN)
    fun delete(id: Long)
}
