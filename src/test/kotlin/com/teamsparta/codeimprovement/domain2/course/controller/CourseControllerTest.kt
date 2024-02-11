package com.teamsparta.codeimprovement.domain2.course.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.teamsparta.codeimprovement.domain2.course.dto.CourseResponse
import com.teamsparta.codeimprovement.domain2.course.service.CourseService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import io.mockk.junit5.MockKExtension
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import com.fasterxml.jackson.core.type.TypeReference
import com.teamsparta.codeimprovement.domain2.course.dto.CreateCourseRequest
import org.springframework.http.HttpStatus


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockKExtension::class)
class CourseControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
) : BehaviorSpec({
    val courseService = mockk<CourseService>(relaxed = true)
    val log = KotlinLogging.logger {}
    Given("새로운 Course를 생성하려고 할 때") {
        When("POST /courses를 호출하면") {
            Then("새로운 Course가 생성되어야 한다.") {
                val createCourseRequest = CreateCourseRequest(
                    title = "new course",
                    description = "description",
                )
                val createdCourseResponse = CourseResponse(
                    id = 1L,
                    title = "new course",
                    description = "description",
                    status = "OPEN",
                    maxApplicants = 30,
                    numApplicants = 0,
                    lectures = emptyList()
                )
                every { courseService.createCourse(any()) } returns createdCourseResponse

                val result = mockMvc.perform(
                    post("/courses")
                        .content(jacksonObjectMapper().writeValueAsString(createCourseRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn()

                result.response.status shouldBe HttpStatus.CREATED.value()

                val created = jacksonObjectMapper().readValue(
                    result.response.contentAsString,
                    object : TypeReference<CourseResponse>() {}
                )
                log.info{"CreateCourseRequest: $createCourseRequest"}
                log.info{"CreatedCourseResponse: $createdCourseResponse"}
                log.info{"Result: $result"}
                log.info{"Created: $created"}
                assertEquals(createdCourseResponse, created)
            }
        }
    }
    // 이하 생략
})