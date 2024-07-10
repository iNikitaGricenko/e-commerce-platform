package com.wolfhack.service.catalog.service;

import com.wolfhack.service.catalog.adapter.database.BrandDatabaseAdapter;
import com.wolfhack.service.catalog.mapper.BrandMapper;
import com.wolfhack.service.catalog.model.domain.Brand;
import com.wolfhack.service.catalog.model.dto.BrandRequestDTO;
import com.wolfhack.service.catalog.model.dto.BrandResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

	private final BrandMapper brandMapper;

	private final BrandDatabaseAdapter brandDatabaseAdapter;

	public Long createBrand(BrandRequestDTO dto) {
		Brand brand = brandMapper.toModel(dto);

		return brandDatabaseAdapter.save(brand);
	}

	public Long partialUpdateBrand(Long id, BrandRequestDTO dto) {
		Brand brand = brandMapper.toModel(dto);

		return brandDatabaseAdapter.partialUpdate(id, brand);
	}

	public Long updateBrand(Long id, BrandRequestDTO dto) {
		Brand brand = brandMapper.toModel(dto);

		return brandDatabaseAdapter.update(id, brand);
	}

	public BrandResponseDTO getBrandById(Long id) {
		return brandMapper.toResponse(
			brandDatabaseAdapter.getById(id)
		);
	}

	public List<BrandResponseDTO> getAllBrands() {
		return brandDatabaseAdapter.getAll()
			.stream()
			.map(brandMapper::toResponse)
			.toList();
	}

	public void deleteBrand(Long id) {
		brandDatabaseAdapter.delete(id);
	}

}
