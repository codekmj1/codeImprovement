package com.teamsparta.codeimprovement.domain.post.model

import com.teamsparta.codeimprovement.domain.post.dto.request.PostRequestDto
import org.springframework.security.core.userdetails.User

class Post(requestDto: PostRequestDto) {
    lateinit var user: User
}