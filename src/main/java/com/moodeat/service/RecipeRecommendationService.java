package com.moodeat.service;

import org.springframework.stereotype.Service;

import com.moodeat.repository.recipe.recommendation.RecipeRecommendationRepository;
import com.moodeat.repository.user.recipe.recommendation.UserRecipeRecommendationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeRecommendationService {
	private final UserRecipeRecommendationRepository userRecipeRecommendationRepository;
	private final RecipeRecommendationRepository recipeRecommendationRepository;

}
