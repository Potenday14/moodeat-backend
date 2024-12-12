package com.moodeat.dto.ingredient;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResponseGetIngredients {

	@Schema(description = "재료 리스트")
	private List<IngredientDto> ingredients;
}
