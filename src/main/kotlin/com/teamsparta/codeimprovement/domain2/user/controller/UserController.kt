package com.teamsparta.codeimprovement.domain2.user.controller

import com.teamsparta.codeimprovement.domain2.user.dto.SignUpRequest
import com.teamsparta.codeimprovement.domain2.user.dto.UpdateUserProfileRequest
import com.teamsparta.codeimprovement.domain2.user.dto.UserResponse
import com.teamsparta.codeimprovement.domain2.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signUp(signUpRequest))
    }

    @PutMapping("/users/{userId}/profile")
    fun updateUserProfile(@PathVariable userId: Long,
                          @RequestBody updateUserProfileRequest: UpdateUserProfileRequest
    ): ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.updateUserProfile(userId, updateUserProfileRequest))
    }
}