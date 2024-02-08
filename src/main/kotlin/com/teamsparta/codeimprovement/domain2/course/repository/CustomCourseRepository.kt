package com.teamsparta.codeimprovement.domain2.course.repository

import com.teamsparta.codeimprovement.domain2.course.model.Course
import com.teamsparta.codeimprovement.domain2.course.model.CourseStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomCourseRepository {

    fun findByPageableAndStatus(pageable: Pageable, status: CourseStatus?): Page<Course>

    fun searchCourseListByTitle(title: String): List<Course>

}