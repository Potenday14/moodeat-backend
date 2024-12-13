package com.moodeat.repository.recipe.recommendation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moodeat.domain.RecipeRecommendation;

@Repository
public interface RecipeRecommendationRepository
	extends JpaRepository<RecipeRecommendation, Long> {
}
