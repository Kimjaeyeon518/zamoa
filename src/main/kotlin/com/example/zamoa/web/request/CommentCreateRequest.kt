package com.example.zamoa.web.request

import com.example.zamoa.domain.dto.CommentDto
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import lombok.Getter

@Getter
data class CommentCreateRequest @JsonCreator constructor(
    @field:NotBlank(message = "댓글 내용은 필수입니다.")
    @param:JsonProperty("content")
    private val content: String,

    @field:NotNull
    private val boardId: Long,

    @field:NotNull
    private val memberId: Long,

    private val parentCommentId: Long? = null,

) {
    fun toServiceDto(): CommentDto {
        return CommentDto(
            boardId = boardId,
            memberId = memberId,
            content = content,
            parentCommentId = parentCommentId
        )
    }
}