package com.moodeat.repository.recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moodeat.domain.Recipe;

public interface RecipeRepository
	extends JpaRepository<Recipe, Long>, RecipeQueryDsl {

	List<Recipe> findDistinctByIngredientsIngredientIdIn(List<Long> ingredientIds);
}
