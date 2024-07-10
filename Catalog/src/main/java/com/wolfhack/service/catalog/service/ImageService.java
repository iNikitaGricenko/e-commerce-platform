package com.wolfhack.service.catalog.service;

import com.wolfhack.service.catalog.adapter.database.ImageDatabaseAdapter;
import com.wolfhack.service.catalog.mapper.ImageMapper;
import com.wolfhack.service.catalog.model.domain.Image;
import com.wolfhack.service.catalog.model.dto.ImageRequestDTO;
import com.wolfhack.service.catalog.model.dto.ImageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

	private final ImageMapper imageMapper;

	private final ImageDatabaseAdapter imageDatabaseAdapter;

	private final KafkaTemplate<String, String> kafkaTemplate;

	public Long create(ImageRequestDTO dto) {
		Image image = imageMapper.toModel(dto);

		Long savedImageId = imageDatabaseAdapter.save(image);

		kafkaTemplate.send("inventory-updates", "Image saved: " + savedImageId);

		return savedImageId;
	}

	public Long partialUpdate(Long id, ImageRequestDTO dto) {
		Image image = imageMapper.toModel(dto);

		return imageDatabaseAdapter.partialUpdate(id, image);
	}

	public Long update(Long id, ImageRequestDTO dto) {
		Image image = imageMapper.toModel(dto);

		return imageDatabaseAdapter.update(id, image);
	}

	public ImageResponseDTO getById(Long id) {
		return imageMapper.toResponse(
			imageDatabaseAdapter.getById(id)
		);
	}

	public void delete(Long id) {
		imageDatabaseAdapter.delete(id);
		kafkaTemplate.send("inventory-updates", "Image deleted: " + id);
	}

}
