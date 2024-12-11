package com.moodeat.dto.recipe;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExampleResponseCreateRecipe {
	@Schema(description = "생성된 요리 레시피", example = "재료: ~~~")
	private String recipeGuide;
}