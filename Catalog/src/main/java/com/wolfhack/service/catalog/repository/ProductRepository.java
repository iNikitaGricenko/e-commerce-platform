package com.wolfhack.service.catalog.repository;

import com.wolfhack.service.catalog.model.domain.Product;
import com.wolfhack.service.catalog.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	List<ProductEntity> findByCategories_Id(Long categoryId);

	List<Product> findByTags_Name(String tagName);

}