package com.teamsparta.codeimprovement.domain2.lecture.service

import com.teamsparta.codeimprovement.domain2.course.repository.CourseRepository
import com.teamsparta.codeimprovement.domain2.exception.ModelNotFoundException
import com.teamsparta.codeimprovement.domain2.lecture.dto.AddLectureRequest
import com.teamsparta.codeimprovement.domain2.lecture.dto.LectureResponse
import com.teamsparta.codeimprovement.domain2.lecture.dto.UpdateLectureRequest
import com.teamsparta.codeimprovement.domain2.lecture.model.Lecture
import com.teamsparta.codeimprovement.domain2.lecture.model.toResponse
import com.teamsparta.codeimprovement.domain2.lecture.repository.LectureRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LectureServiceImpl(
    private val courseRepository: CourseRepository,
    private val lectureRepository: LectureRepository
): LectureService {
    @Transactional
    override fun addLecture(courseId: Long, request: AddLectureRequest): LectureResponse {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)

        val lecture = Lecture(
            title = request.title,
            videoUrl = request.videoUrl,
            course = course
        )
        // Course에 Lecture 추가
        course.addLecture(lecture)
        // Lecture에 영속성을 전파
        courseRepository.save(course)
        return lecture.toResponse()
    }

    override fun getLecture(courseId: Long, lectureId: Long): LectureResponse {
        val lecture = lectureRepository.findByCourseIdAndId(courseId, lectureId)
            ?: throw ModelNotFoundException("Lecture", lectureId)

        return lecture.toResponse()
    }

    override fun getLectureList(courseId: Long): List<LectureResponse> {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        return course.lectures.map { it.toResponse() }
    }

    @Transactional
    override fun updateLecture(
        courseId: Long,
        lectureId: Long,
        request: UpdateLectureRequest
    ): LectureResponse {
        val lecture = lectureRepository.findByCourseIdAndId(courseId, lectureId)
            ?: throw ModelNotFoundException("Lecture", lectureId)

        val (title, videoUrl) = request
        lecture.title = title
        lecture.videoUrl = videoUrl

        return lectureRepository.save(lecture).toResponse()
    }

    @Transactional
    override fun removeLecture(courseId: Long, lectureId: Long) {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val lecture = lectureRepository.findByIdOrNull(lectureId)
            ?: throw ModelNotFoundException("Lecture", lectureId)

        course.removeLecture(lecture)

        // Lecture에 영속성을 전파
        courseRepository.save(course)
    }
}