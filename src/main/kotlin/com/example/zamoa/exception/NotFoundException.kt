package com.example.zamoa.exception

import com.example.zamoa.exception.BaseException
import com.example.zamoa.web.response.ApiResponseCode

class NotFoundException(message: String): BaseException() {
    override var code: ApiResponseCode = ApiResponseCode.NOT_FOUND
    override var message: String = message
}