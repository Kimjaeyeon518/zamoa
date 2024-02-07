package com.example.zamoa.domain.entity

import jakarta.persistence.*

@Entity
class BoardFavorite(
    @Column(name = "member_id")
    val memberId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    val board: Board
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val boardFavoriteId: Long? = null
}