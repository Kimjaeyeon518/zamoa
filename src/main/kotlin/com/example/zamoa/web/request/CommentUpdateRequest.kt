package com.example.zamoa.web.request

import com.example.zamoa.domain.dto.CommentDto
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CommentUpdateRequest(
    @field:NotNull(message = "댓글 ID 는 필수입니다.")
    val commentId: Long?,

    @field:NotNull
    private val boardId: Long,

    @field:NotNull
    private val memberId: Long,

    @field:NotBlank(message = "댓글 내용은 필수입니다.")
    private val content: String,
) {
    fun toServiceDto(): CommentDto {
        return CommentDto(
            commentId = commentId,
            boardId = boardId,
            memberId = memberId,
            content = content
        )
    }
}