package com.teamsparta.codeimprovement.domain.comment.controller

import com.teamsparta.codeimprovement.domain.comment.service.CommentService
import com.teamsparta.codeimprovement.domain.comment.dto.response.ApiResponseDto
import com.teamsparta.codeimprovement.domain.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentController(
    private val commentService: CommentService
) {

    @DeleteMapping("/comments/{id}/like")
    fun deleteLikeComment(@AuthenticationPrincipal userPrincipal: UserPrincipal,
                          @PathVariable id: Long): ResponseEntity<ApiResponseDto> {
        commentService.deleteLikeComment(id, userPrincipal.email)
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponseDto
            ("댓글 좋아요 취소 성공", HttpStatus.ACCEPTED.value()))
    }
}



