package com.teamsparta.codeimprovement.domain2.course.repository

import com.teamsparta.codeimprovement.domain2.course.model.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository : JpaRepository<Course, Long>, CustomCourseRepository {}