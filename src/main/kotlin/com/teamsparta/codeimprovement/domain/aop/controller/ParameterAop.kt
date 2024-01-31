package com.teamsparta.codeimprovement.domain.aop.controller

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Aspect
@Component
class ParameterAop {

    @Pointcut("execution(* com.teamsparta.codeimprovement.domain.aop.controller..*.*(..))")
    fun cut() {}

    @Before("cut()")
    fun before(joinPoint: JoinPoint) {
        val methodSignature = joinPoint.signature as MethodSignature
        val method: Method = methodSignature.method
        println("${method.name} 메서드 실행")

        val args: Array<Any> = joinPoint.args
        for (obj in args) {
            println("type : ${obj.javaClass.simpleName}")
            println("value : $obj")
        }
    }

    @AfterReturning(value = "cut()", returning = "obj")
    fun afterReturn(joinPoint: JoinPoint, obj: Any) {
        println("return obj")
        println(obj)
    }
}