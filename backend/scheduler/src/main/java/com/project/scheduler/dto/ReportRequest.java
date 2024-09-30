package com.project.scheduler.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
public class ReportRequest {
    @NotNull
    private String frequency;
    @NotNull
    private Date time;
    @NotNull
    private String email;
}
