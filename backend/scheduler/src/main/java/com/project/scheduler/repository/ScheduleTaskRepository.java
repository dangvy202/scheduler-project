package com.project.scheduler.repository;

import com.project.scheduler.entity.ScheduleTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleTaskRepository extends JpaRepository<ScheduleTaskEntity,Long> {
}
