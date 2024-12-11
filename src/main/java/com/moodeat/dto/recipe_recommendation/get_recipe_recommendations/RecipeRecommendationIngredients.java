package com.moodeat.dto.recipe_recommendation.get_recipe_recommendations;

import com.moodeat.dto.shared.RecipeIngredientsDTO;
import lombok.Data;

import java.util.List;

@Data
public class RecipeRecommendationIngredients {
	private List<RecipeIngredientsDTO> main;
	private List<RecipeRecommendationSubIngredients> sub;

}
