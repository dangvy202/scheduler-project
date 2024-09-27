package com.project.scheduler.service.Impl;

import com.project.scheduler.dto.ReportRequest;
import com.project.scheduler.dto.ReportResponse;
import com.project.scheduler.entity.ReportEntity;
import com.project.scheduler.repository.ReportRepository;
import com.project.scheduler.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository repository;

    @Override
    public ReportResponse createReport(ReportRequest request) {
        try{
            ReportEntity reportEntity = ReportEntity.builder()
                    .description("NEW PRODUCT")
                    .time(request.getTime())
                    .title("NOTIFICATION")
                    .frequency(request.getFrequency())
                    .build();
            repository.save(reportEntity);
            return null;
        } catch (Exception ex) {
            log.error(ex.getMessage());

        }
    }

    @Override
    public List<ReportResponse> getAllScheduler() {
        return null;
    }
}
