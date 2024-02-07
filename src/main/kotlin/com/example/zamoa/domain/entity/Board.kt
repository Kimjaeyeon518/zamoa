package com.example.zamoa.domain.entity

import com.example.zamoa.domain.entity.common.BaseTimeEntity
import com.example.zamoa.domain.enums.BoardCategory
import com.example.zamoa.domain.enums.MemberRole
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity(name = "board")
@EntityListeners(AuditingEntityListener::class)
@SQLRestriction("deleted_at <> null")
@SQLDelete(sql = "UPDATE board SET deleted_at = CURRENT_TIMESTAMP() WHERE id = ?")
class Board(memberId: Long, category: BoardCategory, title: String, writer: String, content: String): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val boardId: Long? = null   // 게시글 PK

    @OneToMany(mappedBy = "board", cascade = [CascadeType.ALL])     // CascadeType.ALL - Board 삭제 시, 달려있는 댓글들까지 한꺼번에 삭제
    @OrderBy("created_at desc")
    var comments: MutableList<Comment> = mutableListOf()
        protected set

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    val category: BoardCategory = category   // 게시글 카테고리

    @Column(name="member_id")
    val memberId = memberId     // 게시글 작성자 PK

    @Column(name = "writer")
    val writer: String = writer   // 게시글 작성자 이름

    @Column(name = "title")
    var title: String = title   // 게시글 제목
        protected set

    @Column(name = "content")
    var content: String = content   // 게시글 본문 내용
        protected set

    @Column(name = "hit_count")
    var hitCount: Int = 0   // 조회수
        protected set

    @Column(name = "like_count")
    var likeCount: Int = 0   // 좋아요 수
        protected set

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null
        protected set

    fun update(title: String, content: String) {
        this.title = title
        this.content = content
    }

    fun addComment(comment: Comment) {
        this.comments.add(comment)
    }
}