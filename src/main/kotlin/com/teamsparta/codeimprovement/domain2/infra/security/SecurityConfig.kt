package com.teamsparta.codeimprovement.domain2.infra.security

import com.teamsparta.codeimprovement.domain2.infra.security.jwt.CustomAccessDeniedHandler
import com.teamsparta.codeimprovement.domain2.infra.security.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val authenticationEntryPoint: AuthenticationEntryPoint,
    private val accessDeniedHandler: CustomAccessDeniedHandler,

) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.NEVER) }

            .authorizeHttpRequests {
                it.requestMatchers (
//                    TODO("수정 필요")
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/api/**",
                    "/api/v1/login",
                    "/courses/**"
                ).permitAll()
                    .requestMatchers("/api/v1/**").authenticated()
                    //위 URI를 제외하고는 모두 인증과정을 거치겠음.
//                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling {
                it.authenticationEntryPoint(authenticationEntryPoint)
                it.accessDeniedHandler(accessDeniedHandler)
            }
            .build()
    }



}