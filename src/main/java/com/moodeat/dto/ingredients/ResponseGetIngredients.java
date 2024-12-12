package com.moodeat.dto.ingredients;

import java.util.List;

import com.moodeat.dto.shared.IngredientDto;

import lombok.Data;

@Data
public class ResponseGetIngredients {
	private List<IngredientDto> ingredients;
}
