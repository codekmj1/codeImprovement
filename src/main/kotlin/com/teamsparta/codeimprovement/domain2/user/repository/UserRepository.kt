package com.teamsparta.codeimprovement.domain2.user.repository

import com.teamsparta.codeimprovement.domain2.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun existsByEmail(email: String): Boolean
}