package com.teamsparta.codeimprovement.domain2.course.controller

import com.teamsparta.codeimprovement.domain2.course.dto.CourseResponse
import com.teamsparta.codeimprovement.domain2.course.dto.CreateCourseRequest
import com.teamsparta.codeimprovement.domain2.course.dto.UpdateCourseRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/courses")
@RestController
class CourseController {

    @PostMapping
    fun createCourse(@RequestBody createCourseRequest: CreateCourseRequest): ResponseEntity<CourseResponse> {
        TODO("not implemented")
    }

    @GetMapping()
    fun getCourseList(): ResponseEntity<List<CourseResponse>> {
        TODO("not implemented")
    }

    @GetMapping("/{courseId}")
    fun getCourse(@PathVariable courseId: Long): ResponseEntity<CourseResponse> {
        TODO("not implemented")
    }

    @PutMapping("/{courseId}")
    fun updateCourse(
        @PathVariable courseId: Long,
        @RequestBody updateCourseRequest: UpdateCourseRequest
    ): ResponseEntity<CourseResponse> {
        TODO("not implemented")
    }

    @DeleteMapping("/{courseId}")
    fun deleteCourse(@PathVariable courseId: Long): ResponseEntity<Unit> {
        TODO("not implemented")
    }

}