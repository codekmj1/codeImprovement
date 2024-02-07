package com.teamsparta.codeimprovement.domain2.user.controller

import com.teamsparta.codeimprovement.domain2.user.dto.SignUpRequest
import com.teamsparta.codeimprovement.domain2.user.dto.UpdateUserProfileRequest
import com.teamsparta.codeimprovement.domain2.user.dto.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<UserResponse> {
        TODO()
    }

    @PutMapping("/users/{userId}/profile")
    fun updateUserProfile(@PathVariable userId: Long,
                          @RequestBody updateUserProfileRequest: UpdateUserProfileRequest
    ): ResponseEntity<UserResponse> {
        TODO()
    }
}