package com.example.zamoa.exception

import com.example.zamoa.exception.BaseException
import com.example.zamoa.web.response.ApiResponseCode

class ServerException(message: String): BaseException() {
    override var code: ApiResponseCode = ApiResponseCode.INTERNAL_SERVER_ERROR
    override var message: String = message
}