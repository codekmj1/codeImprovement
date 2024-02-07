package com.teamsparta.codeimprovement.domain2.lecture.service

import com.teamsparta.codeimprovement.domain2.lecture.dto.AddLectureRequest
import com.teamsparta.codeimprovement.domain2.lecture.dto.LectureResponse
import com.teamsparta.codeimprovement.domain2.lecture.dto.UpdateLectureRequest

interface LectureService {
    fun getLectureList(courseId: Long): List<LectureResponse>

    fun getLecture(courseId: Long, lectureId: Long): LectureResponse

    fun addLecture(courseId: Long, request: AddLectureRequest): LectureResponse

    fun updateLecture(
        courseId: Long,
        lectureId: Long,
        request: UpdateLectureRequest
    ): LectureResponse

    fun removeLecture(courseId: Long, lectureId: Long)
}