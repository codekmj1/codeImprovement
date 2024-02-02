package com.teamsparta.codeimprovement.domain.thread.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.teamsparta.codeimprovement.domain.thread.model.Channel
import com.teamsparta.codeimprovement.domain.thread.model.QThread
import com.teamsparta.codeimprovement.domain.thread.model.Thread
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional


@Repository
@Transactional(readOnly = true)
class CustomThreadRepositoryImpl(
    val em: EntityManager
) : CustomThreadRepository {
    override fun findAllByChannelAndMessage(channel: Channel, keyword: String): List<Thread> {
        val thread = QThread.thread

        return JPAQueryFactory(em)
            .selectFrom(thread)
            .where(thread.channel.eq(channel)
                .and(thread.message.contains(keyword)))
            .fetch()
    }
}
