package com.wolfhack.service.catalog.controller;

import com.wolfhack.service.catalog.model.dto.TagRequestDTO;
import com.wolfhack.service.catalog.model.dto.TagResponseDTO;
import com.wolfhack.service.catalog.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController("/api/catalog/tags")
@RequiredArgsConstructor
public class TagController {

	private final TagService tagService;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TagResponseDTO getById(@PathVariable Long id) {
		return tagService.getById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@Valid @RequestBody TagRequestDTO tag) {
		return tagService.create(tag);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Long update(@PathVariable Long id, @Valid @RequestBody TagRequestDTO tag) {
		return tagService.update(id, tag);
	}

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Long updatePartial(@PathVariable Long id, @Valid @RequestBody TagRequestDTO category) {
		return tagService.partialUpdate(id, category);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		tagService.delete(id);
	}

}
