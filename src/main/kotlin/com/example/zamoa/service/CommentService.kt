package com.example.zamoa.service

import com.example.zamoa.domain.dto.BoardDto
import com.example.zamoa.domain.dto.CommentDto
import com.example.zamoa.domain.entity.Board
import com.example.zamoa.domain.entity.Comment
import com.example.zamoa.exception.BadRequestException
import com.example.zamoa.exception.NotFoundException
import com.example.zamoa.repository.BoardRepository
import com.example.zamoa.repository.CommentRepository
import com.example.zamoa.repository.MemberRepository
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CommentService(
    private val commentRepository: CommentRepository,
    private val boardRepository: BoardRepository,
    private val memberRepository: MemberRepository
) {
    @Transactional
    fun create(memberId: Long, boardId: Long, dto: CommentDto): CommentDto {
        val member = memberRepository.findById(memberId)
            .orElseThrow { NotFoundException("사용자를 찾을 수 없습니다.") }
        val board = boardRepository.findById(boardId)
            .orElseThrow { NotFoundException("해당 게시글을 찾을 수 없습니다.") }
        val comment = Comment(
            board = board,
            memberId = memberId,
            writer = member.name,
            content = dto.content,
            parentCommentId = dto.parentCommentId
        )
        val savedComment = commentRepository.save(comment)
        return CommentDto.fromEntity(savedComment)
    }

    @Transactional
    fun update(memberId: Long, dto: CommentDto): CommentDto {
        val comment = commentRepository.findById(dto.commentId!!)
            .orElseThrow { NotFoundException("수정할 댓글을 찾을 수 없습니다.") }
        if (comment.memberId != memberId) {
            throw BadRequestException("해당 댓글의 작성자만 수정할 수 있습니다.")
        }
        comment.update(dto.content)
        return CommentDto.fromEntity(comment)
    }

    fun delete(memberId: Long, commentId: Long) {
        val comment = commentRepository.findById(commentId)
            .orElseThrow { NotFoundException("삭제할 댓글을 찾을 수 없습니다.") }
        if (comment.memberId != memberId) {
            throw BadRequestException("해당 댓글의 작성자만 삭제할 수 있습니다.")
        }
        commentRepository.delete(comment)
    }
}