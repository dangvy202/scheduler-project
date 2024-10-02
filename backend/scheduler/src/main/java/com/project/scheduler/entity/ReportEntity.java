package com.project.scheduler.entity;

import com.project.scheduler.enums.ReportEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="report")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "time")
    private Date time;

    @Column(name = "frequency")
    @Enumerated(EnumType.STRING)
    private ReportEnum frequency;

    @ManyToMany(mappedBy = "reports", cascade = CascadeType.ALL)
    private Set<UserEntity> users = new HashSet<>();
}
