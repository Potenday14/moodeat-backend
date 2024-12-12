package com.moodeat.dto.recipe.recommendation;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResponseGetRecipeRecommendations {

	@Schema(description = "추천된 이유", example = "추우니까 따듯한 국물 드세요")
	private String reason;
	@Schema(description = "추천된 이유 - 핵심 키워드")
	private List<String> keywords;
	@Schema(description = "추천된 레시피 리스트")
	private List<RecipeRecommendationDto> recipes;
}
