package com.teamsparta.codeimprovement.domain2.course.service


import com.teamsparta.codeimprovement.domain2.course.dto.CourseResponse
import com.teamsparta.codeimprovement.domain2.course.dto.CreateCourseRequest
import com.teamsparta.codeimprovement.domain2.course.dto.UpdateCourseRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface CourseService {
    fun getPaginatedCourseList(pageable: Pageable, status: String?): Page<CourseResponse>

    fun getCourseById(courseId: Long): CourseResponse

    fun createCourse(request: CreateCourseRequest): CourseResponse

    fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse

    fun deleteCourse(courseId: Long)

    fun searchCourseList(title: String): List<CourseResponse>


}