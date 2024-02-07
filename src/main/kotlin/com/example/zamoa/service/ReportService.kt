package com.example.zamoa.service

import com.example.zamoa.domain.entity.Report
import com.example.zamoa.repository.ReportRepository
import com.example.zamoa.web.request.ReportRequest
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ReportService(
    private val reportRepository: ReportRepository
) {

    @Transactional
    fun create(request: ReportRequest) {
        val report = Report(request.memberId, request.reason)
        reportRepository.save(report)
    }
}