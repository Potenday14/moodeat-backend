package com.moodeat.dto.recipe.recommendation;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResponseGetRecipeRecommendationsById {

	@Schema(description = "추천된 이유", example = "'맛있는 <b>돼지고기</b> 요리를 찾았어! 즐겁게 먹을 수 있는 돼지고기 레시피를 소개할게'")
	private String reason;

	@Schema(description = "추천된 이유 - 핵심 키워드", example = "[\"돼지고기\"]")
	private List<String> keywords;

	@Schema(description = "추천된 레시피 리스트")
	private List<RecipeRecommendationRecipeDto> recipes;
}
