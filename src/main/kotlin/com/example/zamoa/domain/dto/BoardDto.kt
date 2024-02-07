package com.example.zamoa.domain.dto

import com.example.zamoa.domain.entity.Board
import com.example.zamoa.domain.enums.BoardCategory
import java.time.LocalDateTime

data class BoardDto(
    var boardId: Long? = null,
    var memberId: Long,
    var title: String,
    var category: BoardCategory,
    var writer: String,
    var content: String,
    var comments: List<CommentDto>? = null
) {
    lateinit var createdAt: LocalDateTime
    lateinit var updatedAt: LocalDateTime

    companion object {
        fun fromEntity(board: Board): BoardDto {
            val dto = BoardDto(
                boardId = board.boardId,
                memberId = board.memberId,
                title = board.title,
                category = board.category,
                writer = board.writer,
                content = board.content,
            )

            dto.comments = CommentDto.fromEntities(board.comments)
            dto.createdAt = board.createdAt
            dto.updatedAt = board.updatedAt

            return dto
        }

        fun fromEntities(boards: List<Board>): List<BoardDto> {
            return boards.map {
                val dto = BoardDto(
                    boardId = it.boardId,
                    memberId = it.memberId,
                    title = it.title,
                    category = it.category,
                    writer = it.writer,
                    content = it.content
                )
//                dto.comments = CommentDto.fromEntities(it.comments)
                dto.createdAt = it.createdAt
                dto.updatedAt = it.updatedAt

                dto
            }
        }
    }
}