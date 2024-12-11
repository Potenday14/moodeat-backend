package com.moodeat.domain;

import static jakarta.persistence.FetchType.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moodeat.domain.enums.IngredientType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeIngredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_ingredient_id")
	private Long id;

	@JsonIgnore
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "recipe_id")
	private Recipe recipe;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 4)
	private IngredientType type;

	@Column(length = 20)
	private String quantity;

	@Column(length = 20)
	private String name;
}
