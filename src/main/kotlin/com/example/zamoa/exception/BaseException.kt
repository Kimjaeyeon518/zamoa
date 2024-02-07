package com.example.zamoa.exception

import com.example.zamoa.web.response.ApiResponseCode

abstract class BaseException: RuntimeException() {
    open lateinit var code: ApiResponseCode
    override lateinit var message: String
}