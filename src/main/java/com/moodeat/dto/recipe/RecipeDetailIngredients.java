package com.moodeat.dto.recipe;

import java.util.List;

import com.moodeat.dto.recipe.recommendation.RecipeRecommendationSubIngredients;
import com.moodeat.dto.shared.RecipeIngredientsDto;

import lombok.Data;

@Data
public class RecipeDetailIngredients {
	private List<RecipeIngredientsDto> main;
	private List<RecipeRecommendationSubIngredients> sub;
}
