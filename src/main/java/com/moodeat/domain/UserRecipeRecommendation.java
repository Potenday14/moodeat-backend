package com.moodeat.domain;

import static jakarta.persistence.FetchType.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRecipeRecommendation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_recipe_recommendation_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(fetch = LAZY)
	@JoinColumn(name = "character_id")
	private Character character;

	@OneToMany(mappedBy = "userRecipeRecommendation", cascade = CascadeType.ALL)
	private List<RecipeRecommendation> recipes = new ArrayList<>();

	@Column(unique = true, nullable = false, length = 50)
	private String reason;

	@JdbcTypeCode(SqlTypes.JSON)
	private List<String> keywords = new ArrayList<>();

	@JdbcTypeCode(SqlTypes.JSON)
	private List<Message> chatHistories = new ArrayList<>();

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	private LocalDateTime deletedAt;
}
