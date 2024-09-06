package com.wolfhack.service.catalog.service;

import com.wolfhack.service.catalog.adapter.database.TagDatabaseAdapter;
import com.wolfhack.service.catalog.mapper.TagMapper;
import com.wolfhack.service.catalog.model.domain.Tag;
import com.wolfhack.service.catalog.model.dto.TagRequestDTO;
import com.wolfhack.service.catalog.model.dto.TagResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

	private final TagMapper tagMapper;

	private final TagDatabaseAdapter tagDatabaseAdapter;

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public Long create(TagRequestDTO dto) {
		Tag tag = tagMapper.toModel(dto);

		Long savedTagId = tagDatabaseAdapter.save(tag);

		kafkaTemplate.send("inventory-updates", "Tag saved: " + savedTagId);

		return savedTagId;
	}

	public Long partialUpdate(Long id, TagRequestDTO dto) {
		Tag tag = tagMapper.toModel(dto);

		return tagDatabaseAdapter.partialUpdate(id, tag);
	}

	public Long update(Long id, TagRequestDTO dto) {
		Tag tag = tagMapper.toModel(dto);

		return tagDatabaseAdapter.update(id, tag);
	}

	public TagResponseDTO getById(Long id) {
		return tagMapper.toResponse(
			tagDatabaseAdapter.getById(id)
		);
	}

	public void delete(Long id) {
		tagDatabaseAdapter.delete(id);
		kafkaTemplate.send("inventory-updates", "Tag deleted: " + id);
	}

}
