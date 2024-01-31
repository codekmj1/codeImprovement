package com.teamsparta.codeimprovement.domain.exception

import com.teamsparta.codeimprovement.domain.comment.dto.response.ApiResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ApiResponseDto> {
        return ResponseEntity.badRequest().body(ApiResponseDto(e.message
            ?: "Invalid argument", HttpStatus.BAD_REQUEST.value()))
    }
}

