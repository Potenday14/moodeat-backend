package com.moodeat.repository.recipe.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moodeat.domain.RecipeIngredient;

public interface RecipeIngredientRepository
	extends JpaRepository<RecipeIngredient, Long> {
}
