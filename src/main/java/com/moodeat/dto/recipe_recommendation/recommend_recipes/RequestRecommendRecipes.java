package com.moodeat.dto.recipe_recommendation.recommend_recipes;

import com.moodeat.dto.shared.IngredientDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class RequestRecommendRecipes {
	@Schema(description = "캐릭터 ID" , example = "2")
	private Integer characterId;

	private List<IngredientDTO> ingredients;

	private List<RequestRRChatHistoryUnit> chatHistories;
}
