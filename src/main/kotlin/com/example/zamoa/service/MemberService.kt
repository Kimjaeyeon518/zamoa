package com.example.zamoa.service

import com.example.zamoa.exception.BadRequestException
import com.example.zamoa.exception.DuplicateException
import com.example.zamoa.auth.JwtPlugin
import com.example.zamoa.domain.entity.Member
import com.example.zamoa.exception.NotFoundException
import com.example.zamoa.repository.MemberRepository
import com.example.zamoa.web.request.LoginRequest
import com.example.zamoa.web.request.ReportRequest
import com.example.zamoa.web.request.SignUpRequest
import jakarta.transaction.Transactional
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) {
    fun login(request: LoginRequest): String {
        val member = memberRepository.findByUsername(request.username)
            ?: throw BadRequestException("아이디가 일치하지 않습니다.")

        if (!passwordEncoder.matches(request.password, member.password) ) {
            throw BadRequestException("비밀번호가 일치하지 않습니다.")
        }

        return jwtPlugin.generateToken("${member.memberId}:${member.role}")
    }

    fun signUp(request: SignUpRequest): Member {
        val member = Member(
            username = request.username,
            password = passwordEncoder.encode(request.password),
            name = request.name,
            role = request.role,
        )
        try {
            return memberRepository.save(member)
        } catch (e: DataIntegrityViolationException) {
            throw DuplicateException("중복된 ID 입니다.")
        }
    }

    @Transactional
    fun report(request: ReportRequest) {
        val member = memberRepository.findById(request.memberId)
            .orElseThrow { NotFoundException("사용자를 찾을 수 없습니다.") }
        member.report()
    }
}