package com.project.scheduler.entity;

import com.project.scheduler.enums.ReportEnum;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="user_setting")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "frequency")
    private ReportEnum frequency;

    @Column(name = "day_of_week")
    private String dayOfWeek;

    @Column(name = "day_of_month")
    private int dayOfMonth;

    @Column(name = "time_of_day")
    private String timeOfDay;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_report", referencedColumnName = "id")
    private ReportEntity report;
}
