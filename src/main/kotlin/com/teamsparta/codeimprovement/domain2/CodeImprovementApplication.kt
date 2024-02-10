package com.teamsparta.codeimprovement.domain2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class CodeImprovementApplication

fun main(args: Array<String>) {
    runApplication<CodeImprovementApplication>(*args)
}