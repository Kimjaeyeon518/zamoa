package com.example.zamoa.domain.entity

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity(name = "board")
@EntityListeners(AuditingEntityListener::class)
class Report(memberId: Long, reason: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val reportId: Long? = null

    @Column(name="member_id")
    val memberId = memberId

    @Column(name="reason")
    val reason = reason
}