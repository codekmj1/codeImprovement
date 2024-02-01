package com.teamsparta.codeimprovement.domain.thread.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.teamsparta.codeimprovement.domain.thread.model.Channel

class CustomThreadRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : CustomThreadRepository {
    val thread = QThread.thread

    override fun findAllByChannelAndMessage(channel: Channel, keyword: String): List<Thread> {
        return queryFactory.selectFrom(thread)
            .where(thread.channel.eq(channel), thread.message.contains(keyword))
            .fetch()
    }
}