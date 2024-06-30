package com.wolfhack.service.user.adapter.database;

import com.wolfhack.service.user.model.domain.UserToken;

public interface UserTokenDatabaseAdapter extends DatabaseAdapter<UserToken> {

	UserToken getByToken(String token);

	UserToken getByUserId(Long id);

	boolean existsByUserId(Long userId);

}
