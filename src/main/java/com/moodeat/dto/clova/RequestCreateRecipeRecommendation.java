package com.moodeat.dto.clova;

import java.util.List;
import java.util.Map;

import com.moodeat.domain.enums.Mood;
import com.moodeat.dto.recipe.recommendation.MessageDto;

import lombok.Data;

@Data
public class RequestCreateRecipeRecommendation {
	private Mood mood;

	private Map<Long, String> recipeIdAndNames;

	private List<String> ingredientNames;

	private List<MessageDto> chatHistories;
}
