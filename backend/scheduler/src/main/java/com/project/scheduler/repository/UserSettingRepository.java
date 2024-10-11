package com.project.scheduler.repository;

import com.project.scheduler.entity.UserSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSettingRepository extends JpaRepository<UserSettingEntity, Long> {
}
