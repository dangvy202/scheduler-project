package com.project.scheduler.dto;

import com.project.scheduler.dto.result.ResultDTO;
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
    private String frequency;
    private String email;
}
