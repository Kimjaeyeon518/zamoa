package com.example.zamoa.api

import com.example.zamoa.domain.dto.BoardDto
import com.example.zamoa.domain.enums.BoardCategory
import com.example.zamoa.exception.BadRequestException
import com.example.zamoa.service.BoardService
import com.example.zamoa.web.request.BoardCreateRequest
import com.example.zamoa.web.request.BoardUpdateRequest
import com.example.zamoa.web.response.ListResponse
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
class BoardApi(private val boardService: BoardService) {

    @GetMapping("/boards")
    fun findAll(
        @RequestParam(required = false) category: BoardCategory,
        @RequestParam(required = false) search: String,
    ): ResponseEntity<ListResponse<BoardDto>> {
        val boards = boardService.findAllByCategory(category)
        return ResponseEntity(ListResponse.successOf(boards), HttpStatus.OK)
    }

    @GetMapping("/boards/{boardId}")
    fun find(
        @PathVariable boardId: Long
    ): ResponseEntity<SingleResponse<BoardDto>> {
        val board = boardService.findById(boardId)
        return ResponseEntity(SingleResponse.successOf(board), HttpStatus.OK)
    }

    @PostMapping("/boards")
    fun create(
        @AuthenticationPrincipal user: User,
        @Valid @RequestBody request: BoardCreateRequest
    ): ResponseEntity<SingleResponse<BoardDto>> {
        val board = boardService.create(user.username.toLong(), request.toServiceDto())
        return ResponseEntity(SingleResponse.successOf(board), HttpStatus.OK)
    }

    @PutMapping("/boards/{boardId}")
    fun update(
        @AuthenticationPrincipal user: User,
        @PathVariable boardId: Long,
        @Valid @RequestBody request: BoardUpdateRequest
    ): ResponseEntity<SingleResponse<BoardDto>> {
        if (boardId != request.boardId) {
            throw BadRequestException("수정하려는 게시글이 아닙니다.")
        }
        val board = boardService.update(user.username.toLong(), request.toServiceDto())
        return ResponseEntity(SingleResponse.successOf(board), HttpStatus.OK)
    }

    @DeleteMapping("/boards/{boardId}")
    fun delete(
        @AuthenticationPrincipal user: User,
        @PathVariable boardId: Long
    ): ResponseEntity<SingleResponse<String>> {
        boardService.delete(user.username.toLong(), boardId)
        return ResponseEntity(SingleResponse.success(), HttpStatus.OK)
    }
}