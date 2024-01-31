package com.teamsparta.codeimprovement.domain.post.service

import com.teamsparta.codeimprovement.domain.post.dto.request.PostRequestDto
import com.teamsparta.codeimprovement.domain.post.dto.response.PostResponseDto
import com.teamsparta.codeimprovement.domain.post.model.Post
import com.teamsparta.codeimprovement.domain.post.repository.PostRepository
import org.springframework.security.core.userdetails.User

import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    private val postRepository: PostRepository<Post>
): PostService {
    override fun createPost(requestDto: PostRequestDto, user: User): PostResponseDto{
        val post = Post(requestDto)
        post.user = user

        postRepository.save(post)

        return PostResponseDto(post)
    }

}


