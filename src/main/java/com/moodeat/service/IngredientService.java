package com.moodeat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moodeat.domain.Ingredient;
import com.moodeat.dto.ingredient.IngredientDto;
import com.moodeat.dto.ingredient.ResponseGetIngredients;
import com.moodeat.repository.ingredient.IngredientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientService {
	private final IngredientRepository ingredientRepository;

	public ResponseGetIngredients getIngredientsAll() {
		List<Ingredient> allIngredients = ingredientRepository.findAll();
		return changeEntityToDtoIngredientList(allIngredients);
	}

	public ResponseGetIngredients getIngredientsByQuery(String includes) {
		List<Ingredient> queryIngredients
			= ingredientRepository.findByNameContaining(includes);
		return changeEntityToDtoIngredientList(queryIngredients);
	}

	private ResponseGetIngredients changeEntityToDtoIngredientList(List<Ingredient> entityList) {
		List<IngredientDto> dtoList = entityList.stream()
			.map(entity -> {
				IngredientDto dto = new IngredientDto();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				return dto;
			}).toList();
		ResponseGetIngredients responseGetIngredients = new ResponseGetIngredients();
		responseGetIngredients.setIngredients(dtoList);
		return responseGetIngredients;
	}
}
