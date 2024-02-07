package com.example.zamoa.exception

import com.example.zamoa.exception.BaseException
import com.example.zamoa.web.response.ApiResponseCode

class BadRequestException(message: String): BaseException() {
    override var code: ApiResponseCode = ApiResponseCode.BAD_REQUEST
    override var message: String = message
}