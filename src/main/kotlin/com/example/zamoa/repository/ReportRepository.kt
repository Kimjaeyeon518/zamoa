package com.example.zamoa.repository

import com.example.zamoa.domain.entity.Report
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReportRepository: JpaRepository<Report, Long> {

}