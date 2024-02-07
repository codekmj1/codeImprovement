package com.teamsparta.codeimprovement.domain2.lecture.controller


import com.teamsparta.codeimprovement.domain2.lecture.dto.AddLectureRequest
import com.teamsparta.codeimprovement.domain2.lecture.dto.LectureResponse
import com.teamsparta.codeimprovement.domain2.lecture.dto.UpdateLectureRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/courses/{courseId}/lectures")
@RestController
class LectureController {

    @GetMapping
    fun getLectureList(@PathVariable courseId: Long): ResponseEntity<List<LectureResponse>> {
        TODO("not implemented")
    }

    @GetMapping("/{lectureId}")
    fun getLecture(@PathVariable courseId: Long, @PathVariable lectureId: Long): ResponseEntity<LectureResponse> {
        TODO("not implemented")
    }

    @PostMapping()
    fun addLecture(
        @PathVariable courseId: Long,
        @RequestBody addLectureRequest: AddLectureRequest
    ): ResponseEntity<LectureResponse> {
        TODO("not implemented")
    }

    @PutMapping("/{lectureId}")
    fun updateLecture(
        @PathVariable courseId: Long,
        @PathVariable lectureId: Long,
        @RequestBody  updateLectureRequest: UpdateLectureRequest
    ): ResponseEntity<LectureResponse> {
        TODO("not implemented")
    }

    @DeleteMapping("/{lectureId}")
    fun removeLecture(@PathVariable courseId: Long, @PathVariable lectureId: Long): ResponseEntity<Unit> {
        TODO("not implemented")
    }

}