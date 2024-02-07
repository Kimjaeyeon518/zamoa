package com.example.zamoa.api

import com.example.zamoa.service.MemberService
import com.example.zamoa.service.ReportService
import com.example.zamoa.web.request.ReportRequest
import com.example.zamoa.web.response.SingleResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ReportApi(
    private val reportService: ReportService
) {
    @PostMapping("/report")
    fun report(
        @Valid @RequestBody request: ReportRequest
    ): ResponseEntity<SingleResponse<String>> {
        reportService.create(request)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(SingleResponse.success("회원가입 성공"))
    }
}