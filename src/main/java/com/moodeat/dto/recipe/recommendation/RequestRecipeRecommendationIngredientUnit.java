package com.moodeat.dto.recipe.recommendation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestRecipeRecommendationIngredientUnit {

	@Schema(description = "재료 ID", example = "6")
	private Long id;

	@Schema(description = "재료 이름", example = "쌀")
	private String name;
}
