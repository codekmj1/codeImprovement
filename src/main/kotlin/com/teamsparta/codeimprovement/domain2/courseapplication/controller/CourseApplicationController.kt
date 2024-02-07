package com.teamsparta.codeimprovement.domain2.courseapplication.controller

import com.teamsparta.codeimprovement.domain2.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.codeimprovement.domain2.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.codeimprovement.domain2.courseapplication.dto.UpdateApplicationStatusRequest
import com.teamsparta.codeimprovement.domain2.courseapplication.service.CourseApplicationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/courses/{courseId}/applications")
@RestController
class CourseApplicationController(
    private val courseApplicationService: CourseApplicationService
) {

    @PostMapping
    fun applyCourse(
        @PathVariable courseId: Long,
        applyCourseRequest: ApplyCourseRequest
    ): ResponseEntity<CourseApplicationResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(courseApplicationService.applyCourse(courseId, applyCourseRequest))
    }

    @GetMapping()
    fun getApplicationList(@PathVariable courseId: Long): ResponseEntity<List<CourseApplicationResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseApplicationService.getCourseApplicationList(courseId))
    }

    @GetMapping("/{applicationId}")
    fun getApplication(
        @PathVariable courseId: Long,
        @PathVariable applicationId: Long
    ): ResponseEntity<CourseApplicationResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseApplicationService.getCourseApplication(courseId, applicationId))
    }

    @PatchMapping("/{applicationId}")
    fun updateApplicationStatus(
        @PathVariable courseId: Long,
        @PathVariable applicationId: Long,
        @RequestBody updateApplicationStatusRequest: UpdateApplicationStatusRequest
    ): ResponseEntity<CourseApplicationResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                courseApplicationService.updateCourseApplicationStatus(
                    courseId,
                    applicationId,
                    updateApplicationStatusRequest
                )
            )
    }

}