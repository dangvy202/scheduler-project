package com.project.scheduler.repository;

import com.project.scheduler.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    @Query("SELECT u FROM UserEntity u JOIN FETCH u.reports WHERE u.id = :id")
    Optional<UserEntity> findUserAndReportById(long id);
}
