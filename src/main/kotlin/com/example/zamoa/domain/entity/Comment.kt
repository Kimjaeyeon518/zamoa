package com.example.zamoa.domain.entity

import com.example.zamoa.domain.entity.common.BaseTimeEntity
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity(name = "comment")
@EntityListeners(AuditingEntityListener::class)
@SQLRestriction("deleted_at <> null")
@SQLDelete(sql = "UPDATE comment SET deleted_at = CURRENT_TIMESTAMP() WHERE id = ?")
class Comment(
    board: Board,
    memberId: Long,
    parentCommentId: Long? = null,
    writer: String,
    content: String
): BaseTimeEntity() {

    // 객체 레벨 양방향 연관 관계 설정.
    init {
        board.addComment(this)
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val commentId: Long? = null   // 댓글 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    val board: Board = board      // 댓글이 달린 게시물

    @Column(name="member_id")
    val memberId = memberId     // 게시글 작성자 PK

    @Column(name="parent_comment_id")
    val parentCommentId: Long? = parentCommentId  // 부모 댓글 PK

    @Column(name = "writer")
    val writer: String = writer   // 댓글 작성자 이름

    @Column(name = "content")
    var content: String = content   // 댓글 내용
        protected set

    @Column(name = "like_count")
    var likeCount: Int = 0   // 좋아요 수
        protected set

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null
        protected set

    fun update(content: String) {
        this.content = content
    }
}