package com.wolfhack.service.catalog.persistence;

import com.wolfhack.common.exception.NotFoundException;
import com.wolfhack.common.wrapper.DomainPage;
import com.wolfhack.service.catalog.adapter.database.ProductDatabaseAdapter;
import com.wolfhack.service.catalog.mapper.ProductMapper;
import com.wolfhack.service.catalog.model.domain.Product;
import com.wolfhack.service.catalog.model.entity.ProductEntity;
import com.wolfhack.service.catalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDatabaseGateway implements ProductDatabaseAdapter {

	private final ProductRepository productRepository;

	private final ProductMapper productMapper;

	@Override
	public Long save(Product model) {
		ProductEntity entity = productMapper.toEntity(model);

		return productRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, Product model) {
		ProductEntity updated = productRepository.findById(id)
			.map(categoryEntity -> productMapper.partialUpdate(model, categoryEntity))
			.orElseThrow(NotFoundException::new);

		return productRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, Product model) {
		if (!exists(id)) {
			throw new NotFoundException("Product does not exist");
		}

		ProductEntity entity = productMapper.toEntity(model);
		productMapper.update(model, entity);
		return productRepository.save(entity).getId();
	}

	@Override
	public Product getById(Long id) {
		return productRepository
			.findById(id)
			.map(productMapper::toModel)
			.orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return productRepository.existsById(id);
	}

	@Override
	public Collection<Product> getById(Collection<Long> ids) {
		return productRepository
			.findAllById(ids)
			.stream()
			.map(productMapper::toModel)
			.toList();
	}

	@Override
	public List<Product> getAll() {
		return productRepository
			.findAll()
			.stream()
			.map(productMapper::toModel)
			.toList();
	}

	@Override
	public DomainPage<Product> getPage(Pageable pageable) {
		Page<Product> page = productRepository
			.findAll(pageable)
			.map(productMapper::toModel);

		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<Product> getByCategory(Long categoryId) {
		return productRepository
			.findByCategories(categoryId)
			.stream()
			.map(productMapper::toModel)
			.toList();
	}

	@Override
	public List<Product> findByTags(String tagName) {
		return productRepository.findByTags_Name(tagName);
	}

}
