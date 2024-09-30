package com.project.scheduler.service.Impl;

import com.project.scheduler.constant.ReportConstant;
import com.project.scheduler.dto.ReportRequest;
import com.project.scheduler.dto.ReportResponse;
import com.project.scheduler.dto.result.ResultDTO;
import com.project.scheduler.entity.ReportEntity;
import com.project.scheduler.entity.UserEntity;
import com.project.scheduler.repository.ReportRepository;
import com.project.scheduler.repository.UserRepository;
import com.project.scheduler.service.ReportService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResultDTO<ReportResponse> createReport(ReportRequest request) {
        try{
            ReportEntity reportEntity = ReportEntity.builder()
                    .description("NEW PRODUCT")
                    .time(request.getTime())
                    .title("NOTIFICATION")
                    .frequency(request.getFrequency())
                    .build();
            reportRepository.save(reportEntity);
            UserEntity userEntity = UserEntity.builder()
                    .email(request.getEmail())
                    .reports(Set.of(reportEntity))
                    .build();
            userRepository.save(userEntity);
            return ResultDTO.<ReportResponse>builder()
                    .code(200)
                    .status(ReportConstant.REPORT_SUCCESS)
                    .message(ReportConstant.REPORT_SUCCESS)
                    .content(null).build();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResultDTO.<ReportResponse>builder()
                    .code(500)
                    .status(ReportConstant.REPORT_FAIL)
                    .message(ex.getMessage())
                    .content(null).build();
        }
    }

    @Override
    public List<ReportResponse> getAllScheduler() {
        return null;
    }
}
