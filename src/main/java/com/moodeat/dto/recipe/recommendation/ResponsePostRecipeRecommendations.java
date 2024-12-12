package com.moodeat.dto.recipe.recommendation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResponsePostRecipeRecommendations {

	@Schema(description = "완성 후 반환되는 추천 레시피 목록 ID", example = "2")
	private Long recommendationId;
}
