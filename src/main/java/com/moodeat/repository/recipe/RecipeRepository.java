package com.moodeat.repository.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moodeat.domain.Recipe;

public interface RecipeRepository
	extends JpaRepository<Recipe, Long>, RecipeQueryDsl {

}
