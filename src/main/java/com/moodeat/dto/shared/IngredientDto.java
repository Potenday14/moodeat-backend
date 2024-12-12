package com.moodeat.dto.shared;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class IngredientDto {

	@Schema(description = "재료 ID", example = "6")
	private Long id;

	@Schema(description = "재료 이름", example = "쌀")
	private String name;

}
