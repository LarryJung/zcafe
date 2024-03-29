package com.example.shared


import io.ktor.http.*

class CafeException(
    val errorCode: ErrorCode,
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause) {
    constructor(errorCode: ErrorCode) : this(errorCode, errorCode.name, null)
    constructor(errorCode: ErrorCode, message: String) : this(errorCode, message, null)
    constructor(errorCode: ErrorCode, cause: Throwable) : this(errorCode, cause.message ?: "", cause)
}

enum class ErrorCode(val httpStatusCode: HttpStatusCode) {
    USER_NOT_FOUND(HttpStatusCode.Unauthorized),
    PASSWORD_INCORRECT(HttpStatusCode.BadRequest),
    MENU_NOT_FOUND(HttpStatusCode.NotFound),
    ORDER_NOT_FOUND(HttpStatusCode.NotFound),
    USER_ALREADY_EXISTS(HttpStatusCode.BadRequest),
    MENU_ALREADY_EXISTS(HttpStatusCode.BadRequest),
    ORDER_ALREADY_EXISTS(HttpStatusCode.BadRequest),
    INTERNAL_ERROR(HttpStatusCode.InternalServerError),
    ALREADY_LOGGED_IN(HttpStatusCode.BadRequest),
    FORBIDDEN(HttpStatusCode.Forbidden),
    BAD_REQUEST(HttpStatusCode.BadRequest),
}
