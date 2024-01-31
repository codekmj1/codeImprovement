package com.teamsparta.codeimprovement.domain.exception

import java.io.Serial


class TaskNotFoundException : RuntimeException {
    // 사용자가 특정 할 일을 찾으려고 할 때 해당 할 일이 데이터베이스에 존재하지 않는 경우에 발생하는 예외.
    @Serial
    private val serialVersionUID: Long = -4937536120992271478L

    constructor() : super()

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)

    constructor(cause: Throwable) : super(cause)

    constructor(
        message: String,
        cause: Throwable,
        enableSuppression: Boolean,
        writableStackTrace: Boolean
    ) : super(message, cause, enableSuppression, writableStackTrace)
}


class UserNotAuthenticatedException : RuntimeException {
    // 사용자가 로그인하지 않은 상태에서 로그인이 필요한 기능을 이용하려고 할 때 발생하는 예외.
    @Serial
    private val serialVersionUID: Long = -4937536120992271478L

    constructor() : super()

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)

    constructor(cause: Throwable) : super(cause)

    constructor(
        message: String,
        cause: Throwable,
        enableSuppression: Boolean,
        writableStackTrace: Boolean
    ) : super(message, cause, enableSuppression, writableStackTrace)
}

class InvalidTaskStatusException : RuntimeException {
    // 사용자가 할 일의 상태를 변경하려고 할 때, 허용되지 않는 상태로 변경하려고 시도하면 발생하는 예외.
    @Serial
    private val serialVersionUID: Long = -4937536120992271478L

    constructor() : super()

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)

    constructor(cause: Throwable) : super(cause)

    constructor(
        message: String,
        cause: Throwable,
        enableSuppression: Boolean,
        writableStackTrace: Boolean
    ) : super(message, cause, enableSuppression, writableStackTrace)
}