package com.example.zamoa.service

import com.example.zamoa.domain.dto.BoardDto
import com.example.zamoa.domain.entity.Board
import com.example.zamoa.domain.enums.BoardCategory
import com.example.zamoa.exception.BadRequestException
import com.example.zamoa.exception.NotFoundException
import com.example.zamoa.repository.BoardRepository
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class BoardService(private val boardRepository: BoardRepository) {

    fun findAllByCategory(category: BoardCategory): List<BoardDto> {
        val boards = boardRepository.findAllByCategory(category)
        return BoardDto.fromEntities(boards)
    }

    fun findById(boardId: Long): BoardDto {
        val board = boardRepository.findById(boardId)
            .orElseThrow { NotFoundException("해당 게시글을 찾을 수 없습니다.") }
        return BoardDto.fromEntity(board)
    }

    @Transactional
    fun create(memberId: Long, dto: BoardDto): BoardDto {
        val board = Board(
            memberId = memberId,
            category = dto.category,
            title = dto.title,
            writer = dto.writer,
            content = dto.content
        )
        val savedBoard = boardRepository.save(board)
        return BoardDto.fromEntity(savedBoard)
    }

    @Transactional
    fun update(memberId: Long, dto: BoardDto): BoardDto {
        val board = boardRepository.findById(dto.boardId!!)
            .orElseThrow { NotFoundException("수정할 게시글을 찾을 수 없습니다.") }
        if (board.memberId != memberId) {
            throw BadRequestException("해당 게시글의 작성자만 수정할 수 있습니다.")
        }
        board.update(dto.title, dto.content)
        return BoardDto.fromEntity(board)
    }

    fun delete(memberId: Long, boardId: Long) {
        val board = boardRepository.findById(boardId)
            .orElseThrow { NotFoundException("삭제할 게시글을 찾을 수 없습니다.") }
        if (board.memberId != memberId) {
            throw BadRequestException("해당 게시글의 작성자만 삭제할 수 있습니다.")
        }
        boardRepository.delete(board)
    }
}