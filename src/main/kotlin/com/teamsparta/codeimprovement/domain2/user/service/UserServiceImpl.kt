package com.teamsparta.codeimprovement.domain2.user.service

import com.teamsparta.codeimprovement.domain2.exception.ModelNotFoundException
import com.teamsparta.codeimprovement.domain2.user.dto.SignUpRequest
import com.teamsparta.codeimprovement.domain2.user.dto.UpdateUserProfileRequest
import com.teamsparta.codeimprovement.domain2.user.dto.UserResponse
import com.teamsparta.codeimprovement.domain2.user.model.Profile
import com.teamsparta.codeimprovement.domain2.user.model.User
import com.teamsparta.codeimprovement.domain2.user.model.UserRole
import com.teamsparta.codeimprovement.domain2.user.model.toResponse
import com.teamsparta.codeimprovement.domain2.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {

    @Transactional
    override fun signUp(signUpRequest: SignUpRequest): UserResponse {
        if (userRepository.existsByEmail(signUpRequest.email)) {
            throw IllegalStateException("Email is already in use")
        }

        return userRepository.save(
            User(
                email = signUpRequest.email,
                // TODO: 비밀번호 암호화
                password = signUpRequest.password,
                profile = Profile(
                    nickname = signUpRequest.nickname
                ),
                role = when (signUpRequest.role) {
                    UserRole.STUDENT.name -> UserRole.STUDENT
                    UserRole.TUTOR.name -> UserRole.TUTOR
                    else -> throw IllegalArgumentException("Invalid role")
                }
            )
        ).toResponse()
    }

    @Transactional
    override fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        user.profile = Profile(
            nickname = request.nickname
        )

        return userRepository.save(user).toResponse()
    }

}