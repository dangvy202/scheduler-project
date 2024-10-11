package com.project.scheduler.service.Impl;

import com.project.scheduler.constant.ReportConstant;
import com.project.scheduler.dto.SettingResponse;
import com.project.scheduler.dto.result.ResultDTO;
import com.project.scheduler.entity.ScheduleTaskEntity;
import com.project.scheduler.repository.ReportRepository;
import com.project.scheduler.repository.ScheduleTaskRepository;
import com.project.scheduler.repository.UserRepository;
import com.project.scheduler.repository.UserSettingRepository;
import com.project.scheduler.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ScheduleTaskRepository scheduleTaskRepository;
    private final UserSettingRepository userSettingRepository;

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

    @Override
    public ResultDTO<List<SettingResponse>> getAllScheduler() {
        List<ScheduleTaskEntity> scheduleTasks = scheduleTaskRepository.findAll();
        return ResultDTO.<List<SettingResponse>>builder()
                .code(200)
                .status(ReportConstant.REPORT_SUCCESS)
                .message(ReportConstant.REPORT_SUCCESS)
                .content(
                    scheduleTasks.stream().map(x -> SettingResponse.builder()
                            .userSetting(SettingResponse.UserSettingResponse.builder()
                                    .dayOfMonth(x.getUserSetting().getDayOfMonth())
                                    .dayOfWeek(x.getUserSetting().getDayOfWeek())
                                    .frequency(x.getUserSetting().getFrequency().getFrequencyValue())
                                    .timeOfDay(x.getUserSetting().getTimeOfDay())
                                    .user(SettingResponse.UserSettingResponse.UserResponse.builder()
                                            .email(x.getUser().getEmail())
                                            .name(x.getUser().getName())
                                            .build())
                                    .report(SettingResponse.UserSettingResponse.ReportResponse.builder()
                                            .reportName(x.getUserSetting().getReport().getReportName())
                                            .description(x.getUserSetting().getReport().getDescription())
                                            .build())
                                    .build()).build())
                    .collect(Collectors.toList())
                )
                .build();
    }

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
}
