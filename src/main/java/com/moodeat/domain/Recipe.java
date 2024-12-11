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
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_id")
	private Long id;

	@Column(unique = true, nullable = false, length = 20)
	private String name;

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	private List<RecipeIngredient> mainIngredients = new ArrayList<>();

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	private List<RecipeIngredient> subIngredients = new ArrayList<>();

	@Column(unique = true, nullable = false)
	private String mainPhoto;

	@JdbcTypeCode(SqlTypes.JSON)
	private List<ManualStep> manuals = new ArrayList<>();

	private String tip;

	@Column(nullable = false)
	private int minutes;
	private int calories;
	private int carbohydrates;
	private int protein;
	private int fat;
	private int salt;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	private LocalDateTime deletedAt;
}
