package com.kotlin.arsgsg.exception

import org.springframework.http.HttpStatus

enum class BusinessErrorCode(val description: String, val status: HttpStatus) {
    NO_SUCH_ELEMENT("해당 ID의 내용을 찾을 수 없습니다", HttpStatus.BAD_REQUEST)
}