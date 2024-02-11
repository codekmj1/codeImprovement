package com.teamsparta.codeimprovement.domain2.course.service

import com.teamsparta.codeimprovement.domain2.course.dto.CreateCourseRequest
import com.teamsparta.codeimprovement.domain2.course.model.Course
import com.teamsparta.codeimprovement.domain2.course.repository.CourseRepository
import com.teamsparta.codeimprovement.domain2.courseapplication.repository.CourseApplicationRepository
import com.teamsparta.codeimprovement.domain2.courseapplication.service.CourseApplicationServiceImpl
import com.teamsparta.codeimprovement.domain2.exception.ModelNotFoundException
import com.teamsparta.codeimprovement.domain2.lecture.repository.LectureRepository
import com.teamsparta.codeimprovement.domain2.lecture.service.LectureServiceImpl
import com.teamsparta.codeimprovement.domain2.user.repository.UserRepository
import com.teamsparta.codeimprovement.domain2.user.service.UserServiceImpl
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull


@SpringBootTest
@ExtendWith(MockKExtension::class)
class CourseServiceTest  : BehaviorSpec({
    val log = KotlinLogging.logger {}
    extension(SpringExtension)

    afterContainer {
        clearAllMocks()
    }

    // 종속 repository mocking
    val courseRepository = mockk<CourseRepository>()
    val lectureRepository = mockk<LectureRepository>()
    val applicationRepository = mockk<CourseApplicationRepository>()
    val userRepository = mockk<UserRepository>()

    // courseService 생성
    val courseService = CourseServiceImpl(courseRepository)
    val lectureService = LectureServiceImpl(courseRepository, lectureRepository)
    val applicationService = CourseApplicationServiceImpl(courseRepository
        , applicationRepository, userRepository)
    val userService = UserServiceImpl(userRepository)
    Given("Course 목록이 존재하지 않을때") {

        When("특정 Course를 요청하면") {

            Then("ModelNotFoundException이 발생해야 한다.") {
                val courseId = 1L

                every { courseRepository.findByIdOrNull(any()) } returns null

                log.info { "Testing with courseId: $courseId" }
                shouldThrow<ModelNotFoundException> {
                    courseService.getCourseById(courseId)
                }
            }
        }
    }
    Given("새로운 Course를 추가하려고 할 때") {

        When("addCourse() 메소드를 호출하면") {

            Then("새로운 Course가 저장되어야 한다.") {
                val newCourse = CreateCourseRequest("title","description")
                val savedCourse = Course("title").apply { id = 1L }
                every { courseRepository.save(any()) } returns savedCourse

                val result = courseService.createCourse(newCourse)
                log.info{"Test1: $newCourse"}
                log.info{"Test2: $savedCourse"}
                log.info{"Test3: $result"}
                verify { courseRepository.save(match { it.title == newCourse.title }) }
                assertEquals(savedCourse.id, result.id)
                assertEquals(savedCourse.title, result.title)
                assertNotNull(result.id)
            }

        }
    }
})