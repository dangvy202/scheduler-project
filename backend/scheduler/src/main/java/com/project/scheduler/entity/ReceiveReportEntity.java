package com.project.scheduler.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="receive_report")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private ReportEntity report;
}
