package com.wolfhack.service.order.adapter.database;

import com.wolfhack.common.adapter.database.DatabaseAdapter;
import com.wolfhack.service.order.model.domain.Order;

import java.util.List;

public interface OrderDatabaseAdapter extends DatabaseAdapter<Order> {

	List<Order> getByUser(Long userId);

}
