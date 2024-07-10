package com.wolfhack.service.catalog.persistence;

import com.wolfhack.common.exception.NotFoundException;
import com.wolfhack.common.wrapper.DomainPage;
import com.wolfhack.service.catalog.adapter.database.TagDatabaseAdapter;
import com.wolfhack.service.catalog.mapper.TagMapper;
import com.wolfhack.service.catalog.model.domain.Tag;
import com.wolfhack.service.catalog.model.entity.TagEntity;
import com.wolfhack.service.catalog.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagDatabaseGateway implements TagDatabaseAdapter {

	private final TagRepository tagRepository;

	private final TagMapper tagMapper;

	@Override
	public Long save(Tag model) {
		TagEntity entity = tagMapper.toEntity(model);
		return tagRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, Tag model) {
		TagEntity updated = tagRepository.findById(id)
			.map(tagEntity -> tagMapper.partialUpdate(model, tagEntity))
			.orElseThrow(NotFoundException::new);

		return tagRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, Tag model) {
		if (!exists(id)) {
			throw new NotFoundException("Tag item does not exist");
		}
		TagEntity entity = tagMapper.toEntity(model);
		tagMapper.update(model, entity);
		return tagRepository.save(entity).getId();
	}

	@Override
	public Tag getById(Long id) {
		return tagRepository
			.findById(id)
			.map(tagMapper::toModel)
			.orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return tagRepository.existsById(id);
	}

	@Override
	public Collection<Tag> getById(Collection<Long> ids) {
		return tagRepository.findAllById(ids).stream().map(tagMapper::toModel).toList();
	}

	@Override
	public List<Tag> getAll() {
		return tagRepository.findAll().stream().map(tagMapper::toModel).toList();
	}

	@Override
	public DomainPage<Tag> getPage(Pageable pageable) {
		Page<Tag> page = tagRepository.findAll(pageable).map(tagMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		tagRepository.deleteById(id);
	}

}
