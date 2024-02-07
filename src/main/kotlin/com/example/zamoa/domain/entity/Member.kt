package com.example.zamoa.domain.entity

import com.example.zamoa.domain.entity.common.BaseTimeEntity
import com.example.zamoa.domain.enums.MemberRole
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity(name = "member")
@EntityListeners(AuditingEntityListener::class)
@SQLRestriction("deleted_at <> null")
@SQLDelete(sql = "UPDATE member SET deleted_at = CURRENT_TIMESTAMP() WHERE id = ?")
class Member(username: String, password: String, name: String, role: MemberRole): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val memberId: Long? = null

    @Column(name = "username", unique = true)
    var username: String = username   // 회원 ID

    @Column(name = "password")
    var password: String = password   // 회원 비밀번호

    @Column(name = "name")
    var name: String = name   // 회원 이름

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    var role: MemberRole = role   // 회원 이름

    @Column(name = "is_denied_at")
    var isDeniedAt: LocalDateTime? = null

    fun deny() {
        this.isDeniedAt = LocalDateTime.now()
    }
}