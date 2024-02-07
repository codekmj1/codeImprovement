package com.teamsparta.codeimprovement.domain2.courseapplication.dto

import com.teamsparta.codeimprovement.domain2.course.dto.CourseResponse
import com.teamsparta.codeimprovement.domain2.user.dto.UserResponse

data class CourseApplicationResponse(
    val id: Long,
    val course: CourseResponse,
    val user: UserResponse,
    val status: String,
)