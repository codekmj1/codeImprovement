package com.teamsparta.codeimprovement.domain.thread.repository

import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import com.teamsparta.codeimprovement.domain.thread.infra.querydsl.QueryDslSupport
import com.teamsparta.codeimprovement.domain.thread.model.Channel
import com.teamsparta.codeimprovement.domain.thread.model.QThread
import com.teamsparta.codeimprovement.domain.thread.model.Thread
import jakarta.persistence.EntityManager
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional


@Repository
@Transactional(readOnly = true)
class CustomThreadRepositoryImpl(
    val em: EntityManager
) : CustomThreadRepository, QueryDslSupport() {
    val thread = QThread.thread
    // 페이징 처리를 위해 Pageable 인자를 추가하고, 반환 타입을 Page로 변경
    override fun findAllByChannelAndMessage(
        channel: Channel, keyword: String, pageable: Pageable
    ): Page<Thread> {
        // Querydsl의 JPAQueryFactory를 사용하여 쿼리를 생성
        val query = JPAQueryFactory(em)
            .selectFrom(thread)
            .where(thread.channel.eq(channel)
                .and(thread.message.contains(keyword)))

        // 쿼리 결과의 전체 개수를 조회
        val totalCount = query.fetchCount()

        // 페이징 처리를 위해 offset과 limit을 설정
        query.offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
       
        // sort 정보가 있을 경우, 해당 필드로 정렬
        if (pageable.sort.isSorted) {
            pageable.sort.forEach { sort ->
                val direction = sort.direction
                val property = sort.property

                val orderSpecifier = when (property) {
                    "message" -> if (direction.isAscending) thread.message.asc() else thread.message.desc()
                    "channel.name" -> { // Channel의 name 필드를 기준으로 정렬
                        val channelNamePath = Expressions.stringPath(thread, "channel.name")
                        if (direction.isAscending) channelNamePath.asc() else channelNamePath.desc()
                    }
                    // 필요한 필드를 계속 추가합니다.
                    else -> null
                }


                query.orderBy(orderSpecifier)
            }
        }
        // 쿼리를 실행하여 결과를 조회
        val contents = query.fetch()

        // 조회된 결과와 페이징 정보를 이용하여 Page 객체를 생성
        return PageImpl(contents, pageable, totalCount)
    }
}
