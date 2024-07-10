package com.wolfhack.service.catalog.persistence;

import com.wolfhack.common.exception.NotFoundException;
import com.wolfhack.common.wrapper.DomainPage;
import com.wolfhack.service.catalog.adapter.database.BrandDatabaseAdapter;
import com.wolfhack.service.catalog.mapper.BrandMapper;
import com.wolfhack.service.catalog.model.domain.Brand;
import com.wolfhack.service.catalog.model.entity.BrandEntity;
import com.wolfhack.service.catalog.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandDatabaseGateway implements BrandDatabaseAdapter {

	private final BrandRepository brandRepository;

	private final BrandMapper brandMapper;

	@Override
	public Long save(Brand model) {
		BrandEntity entity = brandMapper.toEntity(model);
		return brandRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, Brand model) {
		BrandEntity updated = brandRepository.findById(id)
			.map(brandEntity -> brandMapper.partialUpdate(model, brandEntity))
			.orElseThrow(NotFoundException::new);

		return brandRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, Brand model) {
		if (!exists(id)) {
			throw new NotFoundException("Brand item does not exist");
		}
		BrandEntity entity = brandMapper.toEntity(model);
		brandMapper.update(model, entity);
		return brandRepository.save(entity).getId();
	}

	@Override
	public Brand getById(Long id) {
		return brandRepository
			.findById(id)
			.map(brandMapper::toModel)
			.orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return brandRepository.existsById(id);
	}

	@Override
	public Collection<Brand> getById(Collection<Long> ids) {
		return brandRepository.findAllById(ids).stream().map(brandMapper::toModel).toList();
	}

	@Override
	public List<Brand> getAll() {
		return brandRepository.findAll().stream().map(brandMapper::toModel).toList();
	}

	@Override
	public DomainPage<Brand> getPage(Pageable pageable) {
		Page<Brand> page = brandRepository.findAll(pageable).map(brandMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		brandRepository.deleteById(id);
	}

}
