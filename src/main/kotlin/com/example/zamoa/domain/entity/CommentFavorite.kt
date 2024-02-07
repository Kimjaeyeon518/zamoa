package com.example.zamoa.domain.entity

import jakarta.persistence.*

@Entity
class CommentFavorite(
    @Column(name = "member_id")
    val memberId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="comment_id")
    val comment: Comment
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val commentFavoriteId: Long? = null

}