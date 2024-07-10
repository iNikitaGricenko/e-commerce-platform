package com.wolfhack.service.catalog.adapter.database;

import com.wolfhack.common.adapter.database.DatabaseAdapter;
import com.wolfhack.service.catalog.model.domain.Product;

import java.util.List;

public interface ProductDatabaseAdapter extends DatabaseAdapter<Product> {

    List<Product> getByCategory(Long categoryId);

	List<Product> findByTags(String tagName);

}
