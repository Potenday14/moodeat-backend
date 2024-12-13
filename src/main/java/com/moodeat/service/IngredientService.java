package com.moodeat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
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

	Pageable pageable = Pageable.ofSize(20);

	public ResponseGetIngredients getIngredientsAll() {
		List<Ingredient> allIngredients = ingredientRepository.findAll(pageable).stream().toList();
		List<IngredientDto> dtoList = changeEntityToDtoIngredientList(allIngredients);
		return changeDtoListToResponse(dtoList);
	}

	public ResponseGetIngredients getIngredientsByQuery(String includes) {
		Ingredient ingredient = ingredientRepository.findByName(includes);
		List<Ingredient> queryIngredients
			= ingredientRepository.findByNameContaining(includes, pageable);
		List<IngredientDto> dtoList = changeEntityToDtoIngredientList(queryIngredients);
		setExactMatchToTop(dtoList, ingredient);
		return changeDtoListToResponse(dtoList);
	}

	private List<IngredientDto> changeEntityToDtoIngredientList(List<Ingredient> entityList) {
		List<IngredientDto> dtoList = entityList.stream()
			.map(entity -> {
				IngredientDto dto = new IngredientDto();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				return dto;
			}).collect(Collectors.toList());
		return dtoList;
	}

	private ResponseGetIngredients changeDtoListToResponse(List<IngredientDto> dtoList) {
		ResponseGetIngredients responseGetIngredients = new ResponseGetIngredients();
		responseGetIngredients.setIngredients(dtoList);
		return responseGetIngredients;
	}

	private void setExactMatchToTop(List<IngredientDto> dtoList, Ingredient exactMatch) {
		if (exactMatch == null) {
			return;
		}
		IngredientDto exactMatchDto = dtoList.stream()
			.filter(dto -> dto.getId().equals(exactMatch.getId()))
			.findFirst().orElse(null);
		if (exactMatchDto == null) {
			return;
		}
		dtoList.remove(exactMatchDto);
		dtoList.add(0, exactMatchDto);
	}
}
