package com.teamsparta.codeimprovement.domain.post.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.BooleanExpression
import com.teamsparta.codeimprovement.domain.post.model.Post
import com.teamsparta.codeimprovement.domain.post.model.QPost
import com.teamsparta.codeimprovement.domain.thread.infra.querydsl.QueryDslSupport
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class PostRepositoryImpl : QueryDslSupport(), CustomPostRepository {
    val post = QPost.post
    // query
    override fun search(searchCondition: Map<String, String>): List<Post> {
        return queryFactory
            .selectFrom(post)
            .where(allCond(searchCondition))
            .fetch()
    }

    // BooleanBuilder
    private fun allCond(searchCondition: Map<String, String>): BooleanBuilder {
        val builder = BooleanBuilder()

        builder.and(titleLike(searchCondition["title"]))
        builder.and(categoryEq(searchCondition["category"]))
        builder.and(tagLike(searchCondition["tag"]))
        builder.and(stateEq(searchCondition["stateCode"]))
        builder.and(withInDays(searchCondition["daysAgo"]))

        return builder
    }

    // 조건1
    private fun titleLike(title: String?): BooleanExpression? {
        return if (title.isNullOrEmpty()) null else post.title.contains(title)
    }

    // 조건2
    private fun categoryEq(category: String?): BooleanExpression? {
        return if (category.isNullOrEmpty()) null else post.category.eq(category)
    }

    // 조건3
    private fun tagLike(tag: String?): BooleanExpression? {
        return if (tag.isNullOrEmpty()) null else post.tag.contains(tag)
    }

    // 조건4
    private fun stateEq(stateCode: String?): BooleanExpression? {
        return if (stateCode.isNullOrEmpty()) null else post.state.eq(stateCode)
    }

    // 조건5
    private fun withInDays(daysAgo: String?): BooleanExpression? {
        return if (daysAgo.isNullOrEmpty()) null
        else post.createdDate.after(LocalDateTime.now().minusDays(daysAgo.toLong()))
    }
}