package com.example.zamoa.api

import com.example.zamoa.domain.dto.CommentDto
import com.example.zamoa.exception.BadRequestException
import com.example.zamoa.service.CommentService
import com.example.zamoa.web.request.CommentCreateRequest
import com.example.zamoa.web.request.CommentUpdateRequest
import com.example.zamoa.web.response.SingleResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class CommentApi(private val commentService: CommentService) {

    @PostMapping("/boards/{boardId}/comments")
    fun create(
        @AuthenticationPrincipal user: User,
        @PathVariable boardId: Long,
        @Valid @RequestBody request: CommentCreateRequest
    ): ResponseEntity<SingleResponse<CommentDto>> {
        val board = commentService.create(user.username.toLong(), boardId, request.toServiceDto())
        return ResponseEntity(SingleResponse.successOf(board), HttpStatus.OK)
    }

    @PutMapping("/boards/{boardId}/comments/{commentId}")
    fun update(
        @AuthenticationPrincipal user: User,
        @PathVariable boardId: Long,
        @PathVariable commentId: Long,
        @Valid @RequestBody request: CommentUpdateRequest
    ): ResponseEntity<SingleResponse<CommentDto>> {
        if (commentId != request.commentId) {
            throw BadRequestException("수정하려는 댓글이 아닙니다.")
        }
        val comment = commentService.update(user.username.toLong(), request.toServiceDto())
        return ResponseEntity(SingleResponse.successOf(comment), HttpStatus.OK)
    }

    @DeleteMapping("/boards/{boardId}/comments/{commentId}")
    fun delete(
        @AuthenticationPrincipal user: User,
        @PathVariable boardId: Long,
        @PathVariable commentId: Long,
    ): ResponseEntity<SingleResponse<String>> {
        commentService.delete(user.username.toLong(), commentId)
        return ResponseEntity(SingleResponse.success(), HttpStatus.OK)
    }
}