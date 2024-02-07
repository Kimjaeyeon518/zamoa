package com.example.zamoa.domain.dto

import com.example.zamoa.domain.entity.Comment
import java.time.LocalDateTime

data class CommentDto(
    var commentId: Long? = null,
    var memberId: Long,
    var boardId: Long,
    var content: String,
    var parentCommentId: Long?,
) {
    lateinit var writer: String
    lateinit var createdAt: LocalDateTime
    lateinit var updatedAt: LocalDateTime

    companion object {
        fun fromEntity(comment: Comment): CommentDto {
            val dto = CommentDto(
                commentId = comment.commentId,
                boardId = comment.board.boardId!!,
                memberId = comment.memberId,
                content = comment.content,
                parentCommentId = comment.parentCommentId
            )

            dto.writer = comment.writer
            dto.createdAt = comment.createdAt
            dto.updatedAt = comment.updatedAt

            return dto
        }

        fun fromEntities(comments: List<Comment>): List<CommentDto> {
            return comments.map {
                val dto = CommentDto(
                    commentId = it.commentId,
                    boardId = it.board.boardId!!,
                    memberId = it.memberId,
                    content = it.content,
                    parentCommentId = it.parentCommentId
                )
                dto.writer = it.writer
                dto.createdAt = it.createdAt
                dto.updatedAt = it.updatedAt

                dto
            }
        }
    }
}