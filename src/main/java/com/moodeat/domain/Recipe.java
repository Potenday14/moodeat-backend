package com.moodeat.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import com.moodeat.dto.IngredientData;
import com.moodeat.dto.ManualData;
import com.moodeat.dto.SubIngredientsData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recipe {

	@Id
	@GeneratedValue
	@Column(name = "recipe_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@JdbcTypeCode(SqlTypes.JSON)
	private List<IngredientData> mainIngredients;

	@JdbcTypeCode(SqlTypes.JSON)
	private SubIngredientsData subIngredients;

	private String mainPhoto;

	@JdbcTypeCode(SqlTypes.JSON)
	private List<ManualData> manuals;

	private String tip;
	private String time;
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
