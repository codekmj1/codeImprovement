package com.teamsparta.codeimprovement.domain.post.service

import com.teamsparta.codeimprovement.domain.post.dto.request.PostRequestDto
import com.teamsparta.codeimprovement.domain.post.dto.response.PostResponseDto
import org.springframework.security.core.userdetails.User


interface PostService {
    // requestDto : 계시글 생성 요청 데이터 전송 객체
    // user : 게시글 작성 사용자
    // PostResponseDto : 생성 게시글의 응답 데이터 전송 객체
    fun createPost(requestDto: PostRequestDto, user: User): PostResponseDto
}


