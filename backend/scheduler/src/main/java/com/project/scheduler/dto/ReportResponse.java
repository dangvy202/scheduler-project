package com.project.scheduler.dto;

import com.project.scheduler.dto.result.ResultDTO;
import com.project.scheduler.enums.ReportEnum;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse{
    private String description;
    private String title;
    private Date time;
    private ReportEnum frequency;
    private String email;
}
