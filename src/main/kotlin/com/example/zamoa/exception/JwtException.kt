package com.example.zamoa.exception

import com.example.zamoa.exception.BaseException
import com.example.zamoa.web.response.ApiResponseCode

class JwtException(
    override var code: ApiResponseCode = ApiResponseCode.UNAUTHORIZED,
    override var message: String
): BaseException()