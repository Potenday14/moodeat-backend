package com.moodeat.dto.recipe_recommendation.get_recipe_recommendations;

import com.moodeat.dto.shared.RecipeIngredientsDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class RecipeRecommendationSubIngredients {
	@Schema(description = "재료 분류" , example = "양념장")
	private String name;
	private List<RecipeIngredientsDTO> data;
}
