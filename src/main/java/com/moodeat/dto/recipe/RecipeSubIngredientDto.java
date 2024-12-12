package com.moodeat.dto.recipe;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RecipeSubIngredientDto {

	@Schema(description = "재료 분류", example = "양념장")
	private String name;

	@Schema(description = "재료 리스트")
	private List<RecipeIngredientDto> data;
}
