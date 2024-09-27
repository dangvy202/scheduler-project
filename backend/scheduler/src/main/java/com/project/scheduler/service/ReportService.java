package com.project.scheduler.service;

import com.project.scheduler.dto.ReportRequest;
import com.project.scheduler.dto.ReportResponse;

import java.util.List;

public interface ReportService {
    ReportResponse createReport(ReportRequest request);
    List<ReportResponse> getAllScheduler();
}
