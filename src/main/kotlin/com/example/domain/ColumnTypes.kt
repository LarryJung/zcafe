package com.example.domain


import com.example.shared.CafeUserRole
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table

class EnumListColumnType<T : Enum<T>>(
    private val enumClass: Class<T>,
    private val varcharLength: Int,
) : ColumnType() {
    override fun sqlType() = "VARCHAR(${varcharLength})"

    override fun valueFromDB(value: Any): List<T> {
        if (value is String) {
            return value.split(',').map { java.lang.Enum.valueOf(enumClass, it) }
        }
        throw IllegalArgumentException("Unexpected value of type String expected but ${value::class} found.")
    }

    override fun notNullValueToDB(value: Any): String {
        if (value is List<*>) {
            return value.joinToString(",") { it.toString() }
        }
        return value.toString()
    }
}

fun <T : Enum<T>> Table.enumList(name: String, enumClass: Class<T>, varcharLength: Int): Column<List<CafeUserRole>> =
    registerColumn(name, EnumListColumnType(enumClass, varcharLength))
