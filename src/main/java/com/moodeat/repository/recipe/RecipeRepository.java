package com.moodeat.repository.recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moodeat.domain.Recipe;

public interface RecipeRepository
	extends JpaRepository<Recipe, Long>, RecipeQueryDsl {

	@Query("SELECT DISTINCT r FROM Recipe r JOIN r.ingredients ri WHERE ri.ingredient.id IN :ingredientIds")
	List<Recipe> findByIngredients_Ingredient_IdIn(List<Long> ingredientIds);
}
