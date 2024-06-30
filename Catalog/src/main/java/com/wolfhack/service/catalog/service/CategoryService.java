package com.wolfhack.service.catalog.service;

import com.wolfhack.service.catalog.adapter.database.CategoryDatabaseAdapter;
import com.wolfhack.service.catalog.mapper.CategoryMapper;
import com.wolfhack.service.catalog.model.domain.Category;
import com.wolfhack.service.catalog.model.dto.CategoryRequestDTO;
import com.wolfhack.service.catalog.model.dto.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    private final CategoryDatabaseAdapter categoryDatabaseAdapter;

    public Long createCategory(CategoryRequestDTO dto) {
        Category category = categoryMapper.toModel(dto);

        return categoryDatabaseAdapter.save(category);
    }

    public Long partialUpdateCategory(Long id, CategoryRequestDTO dto) {
        Category category = categoryMapper.toModel(dto);

        return categoryDatabaseAdapter.partialUpdate(id, category);
    }

    public Long updateCategory(Long id, CategoryRequestDTO dto) {
        Category category = categoryMapper.toModel(dto);

        return categoryDatabaseAdapter.update(id, category);
    }

    public CategoryResponseDTO getCategoryById(Long id) {
        return categoryMapper.toResponse(
            categoryDatabaseAdapter.getById(id)
        );
    }

    public List<CategoryResponseDTO> getAllCategories() {
        return categoryDatabaseAdapter.getAll()
            .stream()
            .map(categoryMapper::toResponse)
            .toList();
    }

    public void deleteCategory(Long id) {
        categoryDatabaseAdapter.delete(id);
    }
}
