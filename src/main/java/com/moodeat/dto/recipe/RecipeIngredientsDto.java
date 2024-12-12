package com.moodeat.dto.recipe;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RecipeIngredientsDto {

	@Schema(description = "메인 재료 리스트")
	private List<RecipeIngredientDto> main;

	@Schema(description = "서브 재료 리스트")
	private List<RecipeSubIngredientDto> sub;
}
