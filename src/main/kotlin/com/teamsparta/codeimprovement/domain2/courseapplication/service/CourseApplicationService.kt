package com.teamsparta.codeimprovement.domain2.courseapplication.service

import com.teamsparta.codeimprovement.domain2.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.codeimprovement.domain2.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.codeimprovement.domain2.courseapplication.dto.UpdateApplicationStatusRequest

interface CourseApplicationService {
    fun applyCourse(courseId: Long, request: ApplyCourseRequest): CourseApplicationResponse

    fun getCourseApplication(
        courseId: Long,
        applicationId: Long
    ): CourseApplicationResponse

    fun getCourseApplicationList(courseId: Long): List<CourseApplicationResponse>

    fun updateCourseApplicationStatus(
        courseId: Long,
        applicationId: Long,
        request: UpdateApplicationStatusRequest
    ): CourseApplicationResponse
}