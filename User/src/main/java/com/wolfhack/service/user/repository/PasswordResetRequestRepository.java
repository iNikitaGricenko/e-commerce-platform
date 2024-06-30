package com.wolfhack.service.user.repository;

import com.wolfhack.service.user.model.entity.PasswordResetRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetRequestRepository extends JpaRepository<PasswordResetRequestEntity, Long> {

	Optional<PasswordResetRequestEntity> findByResetToken(String token);

	Optional<PasswordResetRequestEntity> findByUserId(Long id);

}