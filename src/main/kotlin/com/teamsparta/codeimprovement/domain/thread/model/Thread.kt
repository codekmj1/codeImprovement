package com.teamsparta.codeimprovement.domain.thread.model

import jakarta.persistence.*


@Entity
data class Thread(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToOne
    val channel: Channel,

    val message: String
)