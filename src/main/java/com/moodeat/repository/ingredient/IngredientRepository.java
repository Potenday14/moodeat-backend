package com.moodeat.repository.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moodeat.domain.Ingredient;

@Repository
public interface IngredientRepository
	extends JpaRepository<Ingredient, Long> {
}
