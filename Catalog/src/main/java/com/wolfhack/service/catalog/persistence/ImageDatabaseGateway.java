package com.wolfhack.service.catalog.persistence;

import com.wolfhack.common.exception.NotFoundException;
import com.wolfhack.common.wrapper.DomainPage;
import com.wolfhack.service.catalog.adapter.database.ImageDatabaseAdapter;
import com.wolfhack.service.catalog.mapper.ImageMapper;
import com.wolfhack.service.catalog.model.domain.Image;
import com.wolfhack.service.catalog.model.entity.ImageEntity;
import com.wolfhack.service.catalog.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageDatabaseGateway implements ImageDatabaseAdapter {

	private final ImageRepository imageRepository;

	private final ImageMapper imageMapper;

	@Override
	public Long save(Image model) {
		ImageEntity entity = imageMapper.toEntity(model);
		return imageRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, Image model) {
		ImageEntity updated = imageRepository.findById(id)
			.map(imageEntity -> imageMapper.partialUpdate(model, imageEntity))
			.orElseThrow(NotFoundException::new);

		return imageRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, Image model) {
		if (!exists(id)) {
			throw new NotFoundException("Image item does not exist");
		}
		ImageEntity entity = imageMapper.toEntity(model);
		imageMapper.update(model, entity);
		return imageRepository.save(entity).getId();
	}

	@Override
	public Image getById(Long id) {
		return imageRepository
			.findById(id)
			.map(imageMapper::toModel)
			.orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return imageRepository.existsById(id);
	}

	@Override
	public Collection<Image> getById(Collection<Long> ids) {
		return imageRepository.findAllById(ids).stream().map(imageMapper::toModel).toList();
	}

	@Override
	public List<Image> getAll() {
		return imageRepository.findAll().stream().map(imageMapper::toModel).toList();
	}

	@Override
	public DomainPage<Image> getPage(Pageable pageable) {
		Page<Image> page = imageRepository.findAll(pageable).map(imageMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		imageRepository.deleteById(id);
	}

}
