package com.teamsparta.codeimprovement.domain2.course.service


import com.teamsparta.codeimprovement.domain2.course.dto.CourseResponse
import com.teamsparta.codeimprovement.domain2.course.dto.CreateCourseRequest
import com.teamsparta.codeimprovement.domain2.course.dto.UpdateCourseRequest


interface CourseService {
    fun getAllCourseList(): List<CourseResponse>

    fun getCourseById(courseId: Long): CourseResponse

    fun createCourse(request: CreateCourseRequest): CourseResponse

    fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse

    fun deleteCourse(courseId: Long)

    fun searchCourseList(title: String): List<CourseResponse>
}