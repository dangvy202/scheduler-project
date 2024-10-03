package com.project.scheduler.service.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.project.scheduler.constant.ReportConstant;
import com.project.scheduler.dto.ReportRequest;
import com.project.scheduler.dto.ReportResponse;
import com.project.scheduler.dto.result.ResultDTO;
import com.project.scheduler.entity.ReceiveReportEntity;
import com.project.scheduler.entity.ReportEntity;
import com.project.scheduler.entity.UserEntity;
import com.project.scheduler.repository.ReceiveReportRepository;
import com.project.scheduler.repository.ReportRepository;
import com.project.scheduler.repository.UserRepository;
import com.project.scheduler.service.ReportService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ReceiveReportRepository receiveReportRepository;

    @PostConstruct
    public void init() {
        List<ReceiveReportEntity> reportEntity = receiveReportRepository.findAll();
        ScheduledExecutorService thread = Executors.newScheduledThreadPool(5);

        for (ReceiveReportEntity element : reportEntity) {
            thread.submit(() -> {
                // TODO: Setup time client into Scheduler
               while (true) {
                   try {
                       Date dt = new Date();
                       LocalDateTime nextDay = LocalDateTime.from(dt.toInstant()).withHour(8).withMinute(0).withSecond(0).withNano(0).plusDays(1);
                       LocalDateTime nextMonth = LocalDateTime.from(dt.toInstant()).withHour(8).withMinute(0).withSecond(0).withNano(0).plusMonths(1);
                       LocalDateTime nextYear = LocalDateTime.from(dt.toInstant()).withHour(8).withMinute(0).withSecond(0).withNano(0).plusYears(1);
                       LocalDateTime currentTime = LocalDateTime.now();

                       if (currentTime.isAfter(nextDay)) {
                           System.out.println("Check " + element.getReport().getFrequency());
                           System.out.println("Check2 " + element.getReport().getTime());
                           System.out.println("Check " + element.getUser().getEmail());
                           nextDay = nextDay.plusDays(1);
                       }

                       if (currentTime.isAfter(nextMonth)) {
                           System.out.println("Check " + element.getReport().getFrequency());
                           System.out.println("Check2 " + element.getReport().getTime());
                           System.out.println("Check " + element.getUser().getEmail());
                           nextMonth = nextMonth.plusMonths(1);
                       }

                       if (currentTime.isAfter(nextYear)) {
                           System.out.println("Check " + element.getReport().getFrequency());
                           System.out.println("Check2 " + element.getReport().getTime());
                           System.out.println("Check " + element.getUser().getEmail());
                           nextYear = nextYear.plusYears(1);
                       }

                       Thread.sleep(5000);
                   } catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                       break;
                   }
               }
            });
        }
    }

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
    public ResultDTO<List<ReportResponse>> getAllScheduler() {
        List<ReceiveReportEntity> receiveReportEntities = receiveReportRepository.findAll();
        return ResultDTO.<List<ReportResponse>>builder()
                .code(200)
                .status(ReportConstant.REPORT_SUCCESS)
                .message(ReportConstant.REPORT_SUCCESS)
                .content(receiveReportEntities.stream().map(x -> ReportResponse.builder()
                        .description(x.getReport().getDescription())
                        .title(x.getReport().getTitle())
                        .time(x.getReport().getTime())
                        .frequency(x.getReport().getFrequency())
                        .email(x.getUser().getEmail())
                        .build()).collect(Collectors.toList()))
                .build();
    }

    @Override
    public ResultDTO<ReportResponse> getReportById(long id) {
        ReceiveReportEntity reportEntity = receiveReportRepository.findReceiveReportEntityByUserId(id);
        if(reportEntity != null) {
            ReportResponse response = ReportResponse.builder()
                            .description(reportEntity.getReport().getDescription())
                            .title(reportEntity.getReport().getTitle())
                            .time(reportEntity.getReport().getTime())
                            .frequency(reportEntity.getReport().getFrequency())
                            .email(reportEntity.getUser().getEmail())
                            .build();
            return ResultDTO.<ReportResponse>builder()
                    .code(200)
                    .status(ReportConstant.REPORT_SUCCESS)
                    .message(ReportConstant.REPORT_SUCCESS)
                    .content(response)
                    .build();
        }
        return ResultDTO.<ReportResponse>builder()
                .code(404)
                .status(ReportConstant.REPORT_FAIL)
                .message(ReportConstant.REPORT_FAIL)
                .content(null)
                .build();
    }

    @Override
    public ResultDTO<ReportResponse> updateReportById(long id,ReportRequest request) {
        ReceiveReportEntity receiveReportEntity = receiveReportRepository.findReceiveReportEntityByUserId(id);
        if(receiveReportEntity != null) {
            ReportEntity reportEntity = receiveReportEntity.getReport();
            reportEntity.setTime(request.getTime());
            reportEntity.setFrequency(request.getFrequency());
            reportRepository.save(reportEntity);
            return ResultDTO.<ReportResponse>builder()
                    .code(200)
                    .status(ReportConstant.REPORT_SUCCESS)
                    .message(ReportConstant.REPORT_SUCCESS)
                    .content(null)
                    .build();
        }
        return ResultDTO.<ReportResponse>builder()
                .code(404)
                .status(ReportConstant.REPORT_FAIL)
                .message(ReportConstant.REPORT_FAIL)
                .content(null)
                .build();
    }
}
