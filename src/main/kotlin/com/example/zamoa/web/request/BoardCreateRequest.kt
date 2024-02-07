package com.example.zamoa.web.request

import com.example.zamoa.domain.dto.BoardDto
import com.example.zamoa.domain.enums.BoardCategory
import com.example.zamoa.domain.enums.MemberRole
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class BoardCreateRequest(
    @field:NotNull(message = "게시글 카테고리는 필수입니다.")
    private var category: BoardCategory,

    @field:NotNull
    private var memberId: Long,

    @field:NotBlank(message = "게시글 제목은 필수입니다.")
    private val title: String,

    @field:NotBlank(message = "게시글 작성자 이름은 필수입니다.")
    private val writer: String,

    @field:NotBlank(message = "게시글 내용은 필수입니다.")
    private val content: String,
) {

    fun toServiceDto(): BoardDto {
        return BoardDto(
            memberId = memberId,
            category = category,
            title = title,
            writer = writer,
            content = content
        )
    }
}