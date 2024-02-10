package com.teamsparta.codeimprovement.domain2.course.repository

import com.teamsparta.codeimprovement.domain2.course.model.Course
import com.teamsparta.codeimprovement.domain2.course.model.CourseStatus
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort


@ExtendWith(MockitoExtension::class)
class CourseRepositoryTest {
    val log = KotlinLogging.logger {}
    @InjectMocks
    lateinit var courseRepositoryImpl: CourseRepositoryImpl

    @Mock
    lateinit var course: Course

    @Test
    fun `should return course list when search by title`() {
        val title = "Test Course"
        val courseList = listOf(course)
        `when`(courseRepositoryImpl.searchCourseListByTitle(title)).thenReturn(courseList)

        val result = courseRepositoryImpl.searchCourseListByTitle(title)

        log.info { "Search result: $result" }
        assertEquals(courseList, result)
    }

    @Test
    fun `should return course page when find by pageable and status`() {
        val status = CourseStatus.OPEN
        val pageable: Pageable = PageRequest.of(0, 10, Sort.by("id").ascending())
        val coursePage = courseRepositoryImpl.findByPageableAndStatus(pageable, status)

        log.info { "Page result: $coursePage" }
        assertEquals(pageable.pageSize, coursePage.content.size)
    }
}