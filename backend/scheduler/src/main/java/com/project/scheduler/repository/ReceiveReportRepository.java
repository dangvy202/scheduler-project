package com.project.scheduler.repository;

import com.project.scheduler.entity.ReceiveReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiveReportRepository extends JpaRepository<ReceiveReportEntity,Long> {
    ReceiveReportEntity findReceiveReportEntityByUserId(long id);
}
