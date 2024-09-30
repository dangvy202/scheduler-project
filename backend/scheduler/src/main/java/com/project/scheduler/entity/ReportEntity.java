package com.project.scheduler.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="report")
@Getter
@Setter
@Builder
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
    private String frequency;

    @ManyToMany(mappedBy = "reports", cascade = CascadeType.ALL)
    private Set<UserEntity> users = new HashSet<>();
}
