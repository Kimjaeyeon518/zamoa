package com.example.zamoa.exception

import com.example.zamoa.web.response.ApiResponseCode

class InvalidCredentialException(message: String): BaseException() {
    override var code: ApiResponseCode = ApiResponseCode.FORBIDDEN
    override var message: String = message
}