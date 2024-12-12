package com.moodeat.dto.recipe.recommendation;

import java.util.List;

import com.moodeat.dto.shared.RecipeIngredientsDto;

import lombok.Data;

@Data
public class RecipeRecommendationIngredients {
	private List<RecipeIngredientsDto> main;
	private List<RecipeRecommendationSubIngredients> sub;

}
