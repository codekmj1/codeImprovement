package com.teamsparta.codeimprovement.domain.post.repository

import com.teamsparta.codeimprovement.domain.post.model.Post

interface CustomPostRepository {
    fun search(searchCondition: Map<String, String>): List<Post>
}