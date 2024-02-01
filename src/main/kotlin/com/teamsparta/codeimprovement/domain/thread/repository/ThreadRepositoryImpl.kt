package com.teamsparta.codeimprovement.domain.thread.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.stereotype.Repository

@Repository
class ThreadRepositoryImpl(
    private val query: JPAQueryFactory
) : ThreadRepositoryCustom {

    override fun findAllByChannelAndMessage(channel: Channel, keyword: String): List<Thread> {
        return query.selectFrom(QThread.thread)
            .where(
                channelEq(channel),
                messageContains(keyword)
            )
            .fetch()
    }

    private fun channelEq(channel: Channel): BooleanExpression {
        return if (channel != null) QThread.thread.channel.eq(channel)
        else QThread.thread.channel.isNull
    }

    private fun messageContains(keyword: String): BooleanExpression {
        return if (keyword != null) QThread.thread.message.contains(keyword)
        else QThread.thread.message.isNull
    }
}

interface ThreadRepositoryCustom {
    fun findAllByChannelAndMessage(channel: Channel, keyword: String): List<Thread>
}