package com.moodeat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moodeat.domain.ManualStep;
import com.moodeat.domain.Recipe;
import com.moodeat.domain.RecipeIngredient;
import com.moodeat.dto.recipe.ManualStepDto;
import com.moodeat.dto.recipe.RecipeIngredientDto;
import com.moodeat.dto.recipe.ResponseGetRecipesById;
import com.moodeat.repository.recipe.RecipeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeService {
	private final RecipeRepository recipeRepository;

	public ResponseGetRecipesById getRecipe(Long recipeId) throws EntityNotFoundException {
		Recipe recipe = recipeRepository.findById(recipeId)
			.orElseThrow(EntityNotFoundException::new);
		return changeEntityToDtoRecipe(recipe);
	}

	private ResponseGetRecipesById changeEntityToDtoRecipe(Recipe recipe) {
		ResponseGetRecipesById dto = new ResponseGetRecipesById();
		dto.setId(recipe.getId());
		dto.setName(recipe.getName());
		dto.setIngredients(changeEntityToDtoIngredientList(recipe.getIngredients()));
		dto.setMainPhoto(recipe.getMainPhoto());
		dto.setManuals(changeEntityToDtoManualList(recipe.getManuals()));
		dto.setTip(recipe.getTip());
		dto.setReason(recipe.getReason());
		dto.setMinutes(recipe.getMinutes());
		dto.setCalories(recipe.getCalories());
		dto.setCarbohydrates(recipe.getCarbohydrates());
		dto.setProtein(recipe.getProtein());
		dto.setFat(recipe.getFat());
		dto.setSalt(recipe.getSalt());
		dto.setCreatedAt(recipe.getCreatedAt());
		dto.setUpdatedAt(recipe.getUpdatedAt());
		return dto;
	}

	private List<RecipeIngredientDto> changeEntityToDtoIngredientList(
		List<RecipeIngredient> recipeIngredientEntityList) {
		List<RecipeIngredientDto> recipeIngredientDtoList
			= recipeIngredientEntityList.stream()
			.map(entity -> {
				RecipeIngredientDto dto = new RecipeIngredientDto();
				dto.setId(entity.getId());
				dto.setName(entity.getIngredient().getName());
				dto.setQuantity(entity.getQuantity());
				return dto;
			}).toList();
		return recipeIngredientDtoList;
	}

	private List<ManualStepDto> changeEntityToDtoManualList(
		List<ManualStep> manualStepEntitiyList) {
		List<ManualStepDto> manualStepDtoList =
			manualStepEntitiyList.stream()
				.map(entity -> {
					ManualStepDto dto = new ManualStepDto();
					dto.setOrder(entity.getOrder());
					dto.setDescription(entity.getDescription());
					dto.setPhoto(entity.getPhoto());
					return dto;
				}).toList();
		return manualStepDtoList;
	}

	public List<Recipe> getRecipesByIngredientIds(List<Long> ingredientIds) {
		return recipeRepository.findByIngredients_Ingredient_IdIn(ingredientIds);
	}
}
