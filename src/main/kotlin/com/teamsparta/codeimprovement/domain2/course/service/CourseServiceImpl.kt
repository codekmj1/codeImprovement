package com.teamsparta.codeimprovement.domain2.course.service


import com.teamsparta.codeimprovement.domain2.course.dto.CourseResponse
import com.teamsparta.codeimprovement.domain2.course.dto.CreateCourseRequest
import com.teamsparta.codeimprovement.domain2.course.dto.UpdateCourseRequest
import com.teamsparta.codeimprovement.domain2.course.model.Course
import com.teamsparta.codeimprovement.domain2.course.model.CourseStatus
import com.teamsparta.codeimprovement.domain2.course.model.toResponse
import com.teamsparta.codeimprovement.domain2.course.repository.CourseRepository
import com.teamsparta.codeimprovement.domain2.exception.ModelNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository
) : CourseService {
    override fun getPaginatedCourseList(pageable: Pageable, status: String?): Page<CourseResponse> {
        val courseStatus = when (status) {
            "OPEN" -> CourseStatus.OPEN
            "CLOSED" -> CourseStatus.CLOSED
            null -> null
            else -> throw IllegalArgumentException("The status is invalid")
        }

        return courseRepository.findByPageableAndStatus(pageable, courseStatus).map { it.toResponse() }
    }

    override fun getCourseById(courseId: Long): CourseResponse {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        return course.toResponse()
    }

    @Transactional
    override fun createCourse(request: CreateCourseRequest): CourseResponse {
        return courseRepository.save(
            Course(
                title = request.title,
                description = request.description,
                status = CourseStatus.OPEN,
            )
        ).toResponse()
    }

    @Transactional
    override fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val (title, description) = request

        course.title = title
        course.description = description

        return courseRepository.save(course).toResponse()
    }

    @Transactional
    override fun deleteCourse(courseId: Long) {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        courseRepository.delete(course)
    }

    override fun searchCourseList(title: String): List<CourseResponse> {
        return courseRepository.searchCourseListByTitle(title).map { it.toResponse() }
    }


}