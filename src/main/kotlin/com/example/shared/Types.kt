package com.example.shared

enum class CafeMenuCategory { COFFEE, NONCOFFEE, DESSERT, BAKERY }
enum class CafeUserRole { CUSTOMER, ADMINISTER }
enum class CafeOrderStatus(
    val finished: Boolean,
) { READY(false), COMPLETE(true), CANCEL(true) }