package com.moodeat.dto.recipe.recommendation;

import java.util.List;

import com.moodeat.dto.shared.RecipeIngredientsDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RecipeRecommendationSubIngredients {
	@Schema(description = "재료 분류", example = "양념장")
	private String name;
	private List<RecipeIngredientsDto> data;
}
