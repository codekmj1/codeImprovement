package com.teamsparta.codeimprovement.domain.thread.repository

import com.teamsparta.codeimprovement.domain.thread.model.Channel


interface CustomThreadRepository {
    fun findAllByChannelAndMessage(channel: Channel, keyword: String): List<Thread>
}