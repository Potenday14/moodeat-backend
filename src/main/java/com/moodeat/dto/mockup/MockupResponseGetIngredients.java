package com.moodeat.dto.mockup;

import java.util.List;

import com.moodeat.dto.ingredient.IngredientDto;
import com.moodeat.dto.ingredient.ResponseGetIngredients;

public class MockupResponseGetIngredients extends ResponseGetIngredients {

	public MockupResponseGetIngredients() {
		IngredientDto ingredientDto1 = new IngredientDto();
		ingredientDto1.setId(1L);
		ingredientDto1.setName("쌀");

		IngredientDto ingredientDto2 = new IngredientDto();
		ingredientDto2.setId(2L);
		ingredientDto2.setName("계란");

		IngredientDto ingredientDto3 = new IngredientDto();
		ingredientDto3.setId(3L);
		ingredientDto3.setName("돼지고기");

		IngredientDto ingredientDto4 = new IngredientDto();
		ingredientDto4.setId(4L);
		ingredientDto4.setName("소고기");

		List<IngredientDto> ingredients = List.of(ingredientDto1, ingredientDto2, ingredientDto3, ingredientDto4);
		setIngredients(ingredients);

	}
}
