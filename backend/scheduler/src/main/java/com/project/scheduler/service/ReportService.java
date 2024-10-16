package com.project.scheduler.service;

import com.project.scheduler.dto.ReportRequest;
import com.project.scheduler.dto.SettingResponse;
import com.project.scheduler.dto.result.ResultDTO;

import java.util.List;

public interface ReportService {
    ResultDTO<SettingResponse> createReport(ReportRequest request);
    ResultDTO<List<SettingResponse>> getAllScheduler();
//    ResultDTO<ReportResponse> getReportById(long id);
//    ResultDTO<ReportResponse> updateReportById(long id,ReportRequest request);
}
