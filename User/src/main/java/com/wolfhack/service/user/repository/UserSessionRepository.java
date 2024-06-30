package com.wolfhack.service.user.repository;

import com.wolfhack.service.user.model.entity.UserSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSessionEntity, Long> {
}