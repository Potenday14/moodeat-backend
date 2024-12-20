package com.moodeat.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_id")
	private Long id;

	@Column(unique = true, nullable = false)
	private Long originalId;

	@Column(nullable = false, length = 20)
	private String name;

	// @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	// private List<RecipeIngredient> mainIngredients = new ArrayList<>();
	//
	// @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	// private List<RecipeIngredient> subIngredients = new ArrayList<>();

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	private List<RecipeIngredient> ingredients = new ArrayList<>();

	@Column(unique = true, nullable = false)
	private String mainPhoto;

	@JdbcTypeCode(SqlTypes.JSON)
	@Builder.Default
	private List<ManualStep> manuals = new ArrayList<>();

	@Column(length = 50)
	private String tip;

	@Column(nullable = false, length = 50)
	private String reason;

	@Column(nullable = false)
	private int minutes;

	@Column(nullable = false)
	private int calories;

	@Column(nullable = false)
	private int carbohydrates;

	@Column(nullable = false)
	private int protein;

	@Column(nullable = false)
	private int fat;

	@Column(nullable = false)
	private int salt;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	private LocalDateTime deletedAt;
}
