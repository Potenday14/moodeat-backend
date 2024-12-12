package com.moodeat.dto.recipe;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RecipeIngredientDto {

	@Schema(description = "재료 ID", example = "6")
	private Long id;

	@Schema(description = "재료 이름", example = "쌀")
	private String name;

	@Schema(description = "재료 양", example = "100g")
	private String quantity;
}
