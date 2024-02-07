package com.example.zamoa.api

import com.example.zamoa.domain.entity.Member
import com.example.zamoa.web.request.LoginRequest
import com.example.zamoa.web.response.SingleResponse
import com.example.zamoa.service.MemberService
import com.example.zamoa.web.request.ReportRequest
import com.example.zamoa.web.request.SignUpRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberApi(
    private val memberService: MemberService
) {
    @PostMapping("/login")
    fun signIn(
        @Valid @RequestBody request: LoginRequest
    ): ResponseEntity<SingleResponse<String>> {
        val token = memberService.login(request)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SingleResponse.successOf("로그인 성공", token))
    }

    @PostMapping("/signup")
    fun signUp(
        @Valid @RequestBody request: SignUpRequest
    ): ResponseEntity<SingleResponse<String>> {
        memberService.signUp(request)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SingleResponse.success("회원가입 성공"))
    }
}