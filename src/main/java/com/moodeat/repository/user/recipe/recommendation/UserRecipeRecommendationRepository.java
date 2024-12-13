package com.moodeat.repository.user.recipe.recommendation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moodeat.domain.UserRecipeRecommendation;

@Repository
public interface UserRecipeRecommendationRepository
	extends JpaRepository<UserRecipeRecommendation, Long> {
}
