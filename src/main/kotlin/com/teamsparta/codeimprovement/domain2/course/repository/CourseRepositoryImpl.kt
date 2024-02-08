package com.teamsparta.codeimprovement.domain2.course.repository

import com.teamsparta.codeimprovement.domain.infra.querydsl.QueryDslSupport
import com.teamsparta.codeimprovement.domain2.course.model.Course
import com.teamsparta.codeimprovement.domain2.course.model.QCourse
import org.springframework.stereotype.Repository

@Repository
class CourseRepositoryImpl: CustomCourseRepository, QueryDslSupport() {

    private val course = QCourse.course

    override fun searchCourseListByTitle(title: String): List<Course> {
        return queryFactory.selectFrom(course)
            .where(course.title.containsIgnoreCase(title))
            .fetch()
    }

}