package com.teamsparta.codeimprovement.domain.post.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Post (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var tag: String,

    @Column(nullable = false)
    var category: String,

    @Column(nullable = false)
    var state: String,

    @Column(nullable = false)
    var createdDate: LocalDateTime = LocalDateTime.now()
)

