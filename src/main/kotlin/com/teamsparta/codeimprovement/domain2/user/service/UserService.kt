package com.teamsparta.codeimprovement.domain2.user.service

import com.teamsparta.codeimprovement.domain2.user.dto.SignUpRequest
import com.teamsparta.codeimprovement.domain2.user.dto.UpdateUserProfileRequest
import com.teamsparta.codeimprovement.domain2.user.dto.UserResponse


interface UserService {

    fun signUp(signUpRequest: SignUpRequest): UserResponse

    fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse
}