package com.moodeat.dto.recipe.recommendation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RecipeRecommendationRecipeDto {

	@Schema(description = "레시피 ID", example = "1")
	private Long id;

	@Schema(description = "레시피 이름", example = "돼지고기 김치찌개")
	private String name;

	@Schema(description = "레시피 대표 사진", example = "~~~.jpg")
	private String mainPhoto;

	@Schema(description = "레시피 조리 시간(분)", example = "20")
	private int minutes;

	@Schema(description = "레시피 칼로리", example = "128")
	private int calories;
}
