package com.moodeat.dto.ingredients;

import com.moodeat.dto.shared.IngredientDTO;
import lombok.Data;

import java.util.List;

@Data
public class ResponseGetIngredients {
	private List<IngredientDTO> ingredients;
}
