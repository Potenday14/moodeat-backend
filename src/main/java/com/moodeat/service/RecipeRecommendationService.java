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

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeRecommendationService {
	private final UserRecipeRecommendationRepository userRecipeRecommendationRepository;
	private final RecipeRecommendationRepository recipeRecommendationRepository;
	private final RecipeRepository recipeRepository;

	@Transactional
	public Long saveRecipeRecommendation(
		List<Long> recipeIds, Character character, String reason, List<String> keywords, List<MessageDto> chatHistories
	) {
		UserRecipeRecommendation userRecipeRecommendation = UserRecipeRecommendation.builder()
			.character(character)
			.reason(reason)
			.keywords(keywords)
			.chatHistories(chatHistories.stream().map(c ->
				Message.builder()
					.role(MessageRole.valueOf(c.getRole().toUpperCase()))
					.content(c.getContent())
					.build()).toList())
			.build();

		UserRecipeRecommendation savedUserRecipeRecommendation = userRecipeRecommendationRepository.save(
			userRecipeRecommendation);

		for (Long id : recipeIds) {
			Recipe recipe = recipeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
			RecipeRecommendation recipeRecommendation = RecipeRecommendation.builder()
				.userRecipeRecommendation(savedUserRecipeRecommendation)
				.recipe(recipe)
				.build();
			recipeRecommendationRepository.save(recipeRecommendation);
		}

		return savedUserRecipeRecommendation.getId();
	}

	public Optional<UserRecipeRecommendation> getRecipeRecommendationById(Long recommendationId) {
		return userRecipeRecommendationRepository.findById(recommendationId);
	}
}
