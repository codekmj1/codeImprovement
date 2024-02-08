package com.teamsparta.codeimprovement.domain2.course.repository

import com.teamsparta.codeimprovement.domain2.course.model.Course

interface CustomCourseRepository {

    fun searchCourseListByTitle(title: String): List<Course>

}