package com.wolfhack.service.catalog.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.service.catalog.model.entity.TagEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagRequestDTO implements Serializable {

	@Size(min = 3)
	@NotEmpty
	@NotBlank
	private String name;

}