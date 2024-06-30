package com.wolfhack.service.user.adapter.database;

import com.wolfhack.service.user.model.domain.PasswordResetRequest;

public interface PasswordResetRequestDatabaseAdapter extends DatabaseAdapter<PasswordResetRequest> {

	PasswordResetRequest getByToken(String token);

	PasswordResetRequest getByUserId(Long id);

}
