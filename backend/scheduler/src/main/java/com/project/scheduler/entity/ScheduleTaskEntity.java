package com.project.scheduler.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name="schedule_task")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleTaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "schedule_time")
    private LocalDate scheduleTime;

    @Column(name = "status")
    private String status;

    @Column(name = "result")
    private String result;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private Set<UserEntity> user;

    @ManyToMany
    @JoinColumn(name = "id_config", referencedColumnName = "id")
    private Set<UserSetting> userSetting;
}
