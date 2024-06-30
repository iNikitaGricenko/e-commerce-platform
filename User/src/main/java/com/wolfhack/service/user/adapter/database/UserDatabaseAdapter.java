package com.wolfhack.service.user.adapter.database;

import com.wolfhack.service.user.model.domain.User;

public interface UserDatabaseAdapter extends DatabaseAdapter<User> {

	User getByUsername(String username);

	User getByEmail(String email);

}
