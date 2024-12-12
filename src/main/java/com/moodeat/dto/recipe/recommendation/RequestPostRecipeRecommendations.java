package com.moodeat.dto.recipe.recommendation;

import java.util.List;

import com.moodeat.dto.ingredient.IngredientDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestPostRecipeRecommendations {

	@Schema(description = "캐릭터 ID", example = "2")
	private Integer characterId;

	@Schema(description = "사용자 선택 재료 리스트")
	private List<IngredientDto> ingredients;

	@Schema(description = "채팅 내역")
	private List<MessageDto> chatHistories;
}
