package com.project.scheduler.dto;

import com.project.scheduler.enums.ReportEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ReportRequest {
    @NotNull
    private int idReport;
    @NotNull
    private LocalDate scheduleTime;
    @NotNull
    private ReportEnum frequency;
    @NotNull
    private String timeOfDay;
    @NotNull
    private String dayOfWeek;
    @NotNull
    private int dayOfMonth;
    @NotNull
    private String email;
    @NotNull
    private String name;
}
