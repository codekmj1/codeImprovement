package com.teamsparta.codeimprovement.domain2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class CourseRegistrationApplication

fun main(args: Array<String>) {
    runApplication<CourseRegistrationApplication>(*args)
}