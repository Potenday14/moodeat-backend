package com.moodeat.domain;

import static jakarta.persistence.FetchType.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeRecommendation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_recommendation_id")
	private Long id;

	@JsonIgnore
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_recipe_recommendation_id")
	private UserRecipeRecommendation userRecipeRecommendation;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "recipe_id")
	private Recipe recipe;
}
