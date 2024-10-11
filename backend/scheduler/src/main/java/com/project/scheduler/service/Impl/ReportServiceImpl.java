//package com.project.scheduler.service.Impl;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//
//import com.project.scheduler.constant.ReportConstant;
//import com.project.scheduler.dto.ReportRequest;
//import com.project.scheduler.dto.ReportResponse;
//import com.project.scheduler.dto.result.ResultDTO;
//import com.project.scheduler.entity.ScheduleTaskEntity;
//import com.project.scheduler.entity.ReportEntity;
//import com.project.scheduler.entity.UserEntity;
//import com.project.scheduler.enums.ReportEnum;
//import com.project.scheduler.repository.ReceiveReportRepository;
//import com.project.scheduler.repository.ReportRepository;
//import com.project.scheduler.repository.UserRepository;
//import com.project.scheduler.service.ReportService;
//import jakarta.annotation.PostConstruct;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class ReportServiceImpl implements ReportService {
//
//    private final ReportRepository reportRepository;
//    private final UserRepository userRepository;
//    private final ReceiveReportRepository receiveReportRepository;
//
//    @PostConstruct
//    public void init() {
//        List<ScheduleTaskEntity> reportEntity = receiveReportRepository.findAll();
//        ScheduledExecutorService thread = Executors.newScheduledThreadPool(5);
//
//        for (ScheduleTaskEntity element : reportEntity) {
//            thread.submit(() -> {
//               while (true) {
//                   try {
//                       LocalDateTime dateRecord = element.getReport().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//                       LocalDateTime nextDay = dateRecord.plusDays(1);
//                       LocalDateTime nextMonth = dateRecord.plusMonths(1);
//                       LocalDateTime nextYear = dateRecord.plusYears(1);
//                       LocalDateTime currentTime = LocalDateTime.now();
//
//                       if (currentTime.isBefore(nextDay) && element.getReport().getFrequency().equals(ReportEnum.DAILY)) {
//                           sendMailAndUpdateTime(currentTime,nextDay,element);
//                       }
//
//                       if (currentTime.isBefore(nextMonth) && element.getReport().getFrequency().equals(ReportEnum.WEEKLY)) {
//                           sendMailAndUpdateTime(currentTime,nextMonth,element);
//                       }
//
//                       if (currentTime.isBefore(nextYear) && element.getReport().getFrequency().equals(ReportEnum.YEARLY)) {
//                           sendMailAndUpdateTime(currentTime,nextYear,element);
//                       }
//
//                       Thread.sleep(5000);
//                   } catch (InterruptedException e) {
//                       Thread.currentThread().interrupt();
//                       break;
//                   }
//               }
//            });
//        }
//    }
//
//    private void sendMailAndUpdateTime(LocalDateTime currentTime, LocalDateTime nextTime, ScheduleTaskEntity scheduleTaskEntity) {
//        if(currentTime.isEqual(nextTime)) {
//            //update time
//            Date date = Date.from(nextTime.atZone(ZoneId.systemDefault()).toInstant());
//            ReportEntity reportEntity = scheduleTaskEntity.getReport();
//            reportEntity.setTime(date);
//            reportRepository.save(reportEntity);
//        }
//
//    }
//
//    @Override
//    @Transactional
//    public ResultDTO<ReportResponse> createReport(ReportRequest request) {
//        try{
//            ReportEntity reportEntity = ReportEntity.builder()
//                    .description("NEW PRODUCT")
//                    .time(request.getTime())
//                    .title("NOTIFICATION")
//                    .frequency(request.getFrequency())
//                    .build();
//            reportRepository.save(reportEntity);
//            UserEntity userEntity = UserEntity.builder()
//                    .email(request.getEmail())
//                    .reports(Set.of(reportEntity))
//                    .build();
//            userRepository.save(userEntity);
//            return ResultDTO.<ReportResponse>builder()
//                    .code(200)
//                    .status(ReportConstant.REPORT_SUCCESS)
//                    .message(ReportConstant.REPORT_SUCCESS)
//                    .content(null).build();
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//            return ResultDTO.<ReportResponse>builder()
//                    .code(500)
//                    .status(ReportConstant.REPORT_FAIL)
//                    .message(ex.getMessage())
//                    .content(null).build();
//        }
//    }
//
//    @Override
//    public ResultDTO<List<ReportResponse>> getAllScheduler() {
//        List<ScheduleTaskEntity> receiveReportEntities = receiveReportRepository.findAll();
//        return ResultDTO.<List<ReportResponse>>builder()
//                .code(200)
//                .status(ReportConstant.REPORT_SUCCESS)
//                .message(ReportConstant.REPORT_SUCCESS)
//                .content(receiveReportEntities.stream().map(x -> ReportResponse.builder()
//                        .description(x.getReport().getDescription())
//                        .title(x.getReport().getTitle())
//                        .time(x.getReport().getTime())
//                        .frequency(x.getReport().getFrequency())
//                        .email(x.getUser().getEmail())
//                        .build()).collect(Collectors.toList()))
//                .build();
//    }
//
//    @Override
//    public ResultDTO<ReportResponse> getReportById(long id) {
//        ScheduleTaskEntity reportEntity = receiveReportRepository.findReceiveReportEntityByUserId(id);
//        if(reportEntity != null) {
//            ReportResponse response = ReportResponse.builder()
//                            .description(reportEntity.getReport().getDescription())
//                            .title(reportEntity.getReport().getTitle())
//                            .time(reportEntity.getReport().getTime())
//                            .frequency(reportEntity.getReport().getFrequency())
//                            .email(reportEntity.getUser().getEmail())
//                            .build();
//            return ResultDTO.<ReportResponse>builder()
//                    .code(200)
//                    .status(ReportConstant.REPORT_SUCCESS)
//                    .message(ReportConstant.REPORT_SUCCESS)
//                    .content(response)
//                    .build();
//        }
//        return ResultDTO.<ReportResponse>builder()
//                .code(404)
//                .status(ReportConstant.REPORT_FAIL)
//                .message(ReportConstant.REPORT_FAIL)
//                .content(null)
//                .build();
//    }
//
//    @Override
//    public ResultDTO<ReportResponse> updateReportById(long id,ReportRequest request) {
//        ScheduleTaskEntity scheduleTaskEntity = receiveReportRepository.findReceiveReportEntityByUserId(id);
//        if(scheduleTaskEntity != null) {
//            ReportEntity reportEntity = scheduleTaskEntity.getReport();
//            reportEntity.setTime(request.getTime());
//            reportEntity.setFrequency(request.getFrequency());
//            reportRepository.save(reportEntity);
//            return ResultDTO.<ReportResponse>builder()
//                    .code(200)
//                    .status(ReportConstant.REPORT_SUCCESS)
//                    .message(ReportConstant.REPORT_SUCCESS)
//                    .content(null)
//                    .build();
//        }
//        return ResultDTO.<ReportResponse>builder()
//                .code(404)
//                .status(ReportConstant.REPORT_FAIL)
//                .message(ReportConstant.REPORT_FAIL)
//                .content(null)
//                .build();
//    }
//}
