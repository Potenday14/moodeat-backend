package com.moodeat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moodeat.domain.Character;
import com.moodeat.domain.Message;
import com.moodeat.domain.Recipe;
import com.moodeat.domain.RecipeRecommendation;
import com.moodeat.domain.UserRecipeRecommendation;
import com.moodeat.domain.enums.MessageRole;
import com.moodeat.dto.recipe.recommendation.MessageDto;
import com.moodeat.repository.recipe.RecipeRepository;
import com.moodeat.repository.recipe.recommendation.RecipeRecommendationRepository;
import com.moodeat.repository.user.recipe.recommendation.UserRecipeRecommendationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeRecommendationService {
	private final UserRecipeRecommendationRepository userRecipeRecommendationRepository;
	private final RecipeRecommendationRepository recipeRecommendationRepository;
	private final RecipeRepository recipeRepository;

	@Transactional
	public Long saveRecipeRecommendation(
		List<Long> recipeIds, List<MessageDto> chatHistories, List<String> keywords, String reason,
		Character character
	) {
		UserRecipeRecommendation userRecipeRecommendation = UserRecipeRecommendation.builder()
			.chatHistories(chatHistories.stream().map(c ->
				Message.builder()
					.role(MessageRole.valueOf(c.getRole().toUpperCase()))
					.content(c.getContent()).build()).toList())
			.keywords(keywords)
			.reason(reason).character(character).build();
		UserRecipeRecommendation savedUserRecipeRecommendation = userRecipeRecommendationRepository.save(
			userRecipeRecommendation);

		for (Long id : recipeIds) {
			Recipe recipe = recipeRepository.findById(id).get();
			RecipeRecommendation recipeRecommendation = RecipeRecommendation.builder()
				.recipe(recipe)
				.userRecipeRecommendation(savedUserRecipeRecommendation)
				.build();
			recipeRecommendationRepository.save(recipeRecommendation);
		}

		return savedUserRecipeRecommendation.getId();
	}

	public Optional<UserRecipeRecommendation> getRecipeRecommendationById(Long recommendationId) {
		return userRecipeRecommendationRepository.findById(recommendationId);
	}
}
