package com.teamsparta.codeimprovement.domain.thread.repository

import com.teamsparta.codeimprovement.domain.thread.model.Channel
import com.teamsparta.codeimprovement.domain.thread.model.Thread


interface CustomThreadRepository {
    fun findAllByChannelAndMessage(channel: Channel, keyword: String): List<Thread>
}