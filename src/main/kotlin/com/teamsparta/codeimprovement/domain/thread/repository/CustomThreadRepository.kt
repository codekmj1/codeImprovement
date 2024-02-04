package com.teamsparta.codeimprovement.domain.thread.repository

import com.teamsparta.codeimprovement.domain.thread.model.Channel
import com.teamsparta.codeimprovement.domain.thread.model.Thread
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface CustomThreadRepository {
    fun findAllByChannelAndMessage(channel: Channel, keyword: String, pageable: Pageable): Page<Thread>
}