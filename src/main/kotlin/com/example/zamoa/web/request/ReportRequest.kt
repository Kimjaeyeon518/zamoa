package com.example.zamoa.web.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ReportRequest(
    @field:NotNull
    var memberId: Long,

    @field:NotBlank
    var reason: String
)