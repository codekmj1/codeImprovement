package com.teamsparta.codeimprovement.domain2.course.repository

import com.teamsparta.codeimprovement.domain2.course.model.Course
import com.teamsparta.codeimprovement.domain2.course.model.CourseStatus
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

@SpringBootTest
@ExtendWith(MockKExtension::class)
class CourseRepositoryTest: BehaviorSpec({
    val log = KotlinLogging.logger {}
    // Mock the CourseRepositoryImpl
    val courseRepository = mockk<CourseRepositoryImpl>(relaxed = true)

    Given("새로운 Course를 검색하려고 할 때") {

        When("searchCourseListByTitle() 메소드를 호출하면") {

            Then("일치하는 Course가 반환되어야 한다.") {
                val title = "title"
                val courseList = listOf(Course("title"))

                every { courseRepository.searchCourseListByTitle(title) } returns courseList

                val found = courseRepository.searchCourseListByTitle(title)
                log.info{"Title: $title"}
                log.info{"CourseList: $courseList"}
                log.info{"Found: $found"}
                assertEquals(courseList, found)
            }
        }
    }

    Given("특정 상태의 Course를 페이지 단위로 검색하려고 할 때") {

        When("findByPageableAndStatus() 메소드를 호출하면") {

            Then("일치하는 Course의 페이지가 반환되어야 한다.") {
                val pageable: Pageable = PageRequest.of(0, 10)
                val status: CourseStatus = CourseStatus.OPEN
                val coursePage: Page<Course> = mockk(relaxed = true)

                every { courseRepository.findByPageableAndStatus(pageable, status) } returns coursePage

                val found = courseRepository.findByPageableAndStatus(pageable, status)
                log.info{"Pageable: $pageable"}
                log.info{"Status: $status"}
                log.info{"CoursePage: $coursePage"}
                log.info{"Found: $found"}
                assertEquals(coursePage, found)
            }
        }
    }

})