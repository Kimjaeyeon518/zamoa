package com.example.zamoa.web.request

import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @field:NotBlank
    var username: String,

    @NotBlank
    var password: String
)