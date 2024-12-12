package com.moodeat.dto.recipe.recommendation;

import java.util.List;

import com.moodeat.dto.shared.IngredientDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestRecommendRecipes {
	@Schema(description = "캐릭터 ID", example = "2")
	private Integer characterId;

	private List<IngredientDto> ingredients;

	private List<RequestRecipeRecommendationChatHistoryUnit> chatHistories;
}
