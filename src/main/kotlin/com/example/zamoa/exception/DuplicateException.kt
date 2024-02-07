package com.example.zamoa.exception

import com.example.zamoa.web.response.ApiResponseCode
import com.example.zamoa.exception.BaseException

class DuplicateException(message: String): BaseException() {
    override var code: ApiResponseCode = ApiResponseCode.DUPLICATE_ENTITY
    override var message: String = message
}