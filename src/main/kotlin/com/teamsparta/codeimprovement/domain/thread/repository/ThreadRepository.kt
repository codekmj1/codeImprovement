package com.teamsparta.codeimprovement.domain.thread.repository


import org.springframework.data.jpa.repository.JpaRepository

interface ThreadRepository : JpaRepository<Thread, Long> {
    fun findAllByChannelAndMessage(channel: Channel, keyword: String): List<Thread>
}