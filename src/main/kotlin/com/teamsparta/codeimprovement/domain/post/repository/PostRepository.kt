package com.teamsparta.codeimprovement.domain.post.repository

import com.teamsparta.codeimprovement.domain.post.model.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>, CustomPostRepository