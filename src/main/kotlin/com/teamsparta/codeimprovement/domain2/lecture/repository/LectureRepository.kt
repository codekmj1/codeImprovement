package com.teamsparta.codeimprovement.domain2.lecture.repository

import com.teamsparta.codeimprovement.domain2.lecture.model.Lecture
import org.springframework.data.jpa.repository.JpaRepository

interface LectureRepository: JpaRepository<Lecture, Long> {

    fun findByCourseIdAndId(courseId: Long, lectureId: Long): Lecture?
}