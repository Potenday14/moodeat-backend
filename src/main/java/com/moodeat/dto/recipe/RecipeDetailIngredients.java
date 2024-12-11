package com.moodeat.dto.recipe;

import com.moodeat.dto.recipe_recommendation.get_recipe_recommendations.RecipeRecommendationSubIngredients;
import com.moodeat.dto.shared.RecipeIngredientsDTO;
import lombok.Data;

import java.util.List;

@Data
public class RecipeDetailIngredients {
	private List<RecipeIngredientsDTO> main;
	private List<RecipeRecommendationSubIngredients> sub;
}
