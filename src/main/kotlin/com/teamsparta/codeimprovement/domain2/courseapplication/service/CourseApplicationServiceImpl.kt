package com.teamsparta.codeimprovement.domain2.courseapplication.service

import com.teamsparta.codeimprovement.domain2.course.repository.CourseRepository
import com.teamsparta.codeimprovement.domain2.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.codeimprovement.domain2.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.codeimprovement.domain2.courseapplication.dto.UpdateApplicationStatusRequest
import com.teamsparta.codeimprovement.domain2.courseapplication.model.CourseApplication
import com.teamsparta.codeimprovement.domain2.courseapplication.model.CourseApplicationStatus
import com.teamsparta.codeimprovement.domain2.courseapplication.model.toResponse
import com.teamsparta.codeimprovement.domain2.courseapplication.repository.CourseApplicationRepository
import com.teamsparta.codeimprovement.domain2.exception.ModelNotFoundException
import com.teamsparta.codeimprovement.domain2.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CourseApplicationServiceImpl(
    private val courseRepository: CourseRepository,
    private val courseApplicationRepository: CourseApplicationRepository,
    private val userRepository: UserRepository
) : CourseApplicationService {
    @Transactional
    override fun applyCourse(courseId: Long, request: ApplyCourseRequest): CourseApplicationResponse {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val user = userRepository.findByIdOrNull(request.userId)
            ?: throw ModelNotFoundException("User", request.userId)

        // Course 마감여부 체크
        if (course.isClosed()) {
            throw IllegalStateException("Course is already closed. courseId: $courseId")
        }

        // CourseApplication 중복 체크
        if (courseApplicationRepository.existsByCourseIdAndUserId(courseId, request.userId)) {
            throw IllegalStateException("Already applied. courseId: $courseId, userId: ${request.userId}")
        }

        val courseApplication = CourseApplication(
            course = course,
            user = user,
        )
        course.addCourseApplication(courseApplication)
        // CourseApplication에 영속성을 전파
        courseRepository.save(course)

        return courseApplication.toResponse()
    }

    override fun getCourseApplication(courseId: Long, applicationId: Long): CourseApplicationResponse {
        val application = courseApplicationRepository.findByCourseIdAndId(courseId, applicationId)
            ?: throw ModelNotFoundException("CourseApplication", applicationId)

        return application.toResponse()
    }

    override fun getCourseApplicationList(courseId: Long): List<CourseApplicationResponse> {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)

        return course.courseApplications.map { it.toResponse() }
    }

    @Transactional
    override fun updateCourseApplicationStatus(
        courseId: Long,
        applicationId: Long,
        request: UpdateApplicationStatusRequest
    ): CourseApplicationResponse {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val application = courseApplicationRepository.findByCourseIdAndId(courseId, applicationId)
            ?: throw ModelNotFoundException("CourseApplication", applicationId)

        // 이미 승인 혹은 거절된 신청건인지 체크
        if (application.isProceeded()) {
            throw IllegalStateException("Application is already proceeded. applicationId: $applicationId")
        }

        // Course 마감여부 체크
        if (course.isClosed()) {
            throw IllegalStateException("Course is already closed. courseId: $courseId")
        }

        // 승인 / 거절 따른 처리
        when (request.status) {
            // 승인 일때
            CourseApplicationStatus.ACCEPTED.name -> {
                // 승인 처리
                application.accept()
                // Course의 신청 인원 늘려줌
                course.addApplicant()
                // 만약 신청 인원이 꽉 찬다면 마감 처리
                if (course.isFull()) {
                    course.close()
                }
                courseRepository.save(course)
            }

            // 거절 일때
            CourseApplicationStatus.REJECTED.name -> {
                // 거절 처리
                application.reject()
            }
            // 승인 거절이 아닌 다른 인자가 들어올 경우 에러 처리
            else -> {
                throw IllegalArgumentException("Invalid status: ${request.status}")
            }
        }

        return courseApplicationRepository.save(application).toResponse()
    }
}

