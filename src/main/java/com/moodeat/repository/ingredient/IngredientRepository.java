package com.moodeat.repository.ingredient;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moodeat.domain.Ingredient;

@Repository
public interface IngredientRepository
	extends JpaRepository<Ingredient, Long> {
	List<Ingredient> findByNameContaining(String name, Pageable pageable);
}
