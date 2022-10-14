package com.kotlin.arsgsg.exception

import java.time.LocalDateTime

data class ErrorCommonResponse private constructor(val code: String, val description: String, val createAt: LocalDateTime) {
    companion object {
        fun of(errorCode: BusinessErrorCode): ErrorCommonResponse {
            return ErrorCommonResponse(
                code = "ERR_" + errorCode.ordinal,
                description = errorCode.description,
                createAt = LocalDateTime.now()
            )
        }
    }
}

open class BusinessException(open val errorCode: BusinessErrorCode) :
    RuntimeException(errorCode.description)

data class EntityNotFoundException(override val errorCode: BusinessErrorCode = BusinessErrorCode.NO_SUCH_ELEMENT) :
    BusinessException(errorCode)