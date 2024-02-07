package com.example.zamoa.repository

import com.example.zamoa.domain.entity.Board
import com.example.zamoa.domain.enums.BoardCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository: JpaRepository<Board, Long> {
    fun findAllByCategory(category: BoardCategory): List<Board>
}