package com.teamsparta.codeimprovement.domain2.course.dto

import com.teamsparta.codeimprovement.domain2.lecture.dto.LectureResponse

data class CourseResponse(
    val id: Long,
    val title: String,
    val description: String?,
    val status: String,
    val maxApplicants: Int,
    val numApplicants: Int,
    val lectures: List<LectureResponse>,
)