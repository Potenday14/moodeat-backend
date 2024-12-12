package com.moodeat.dto.recipe.recommendation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResponseRecommendRecipes {
	@Schema(description = "완성 후 반환되는 추천 ID", example = "2")
	private Long recommendationId;
}
