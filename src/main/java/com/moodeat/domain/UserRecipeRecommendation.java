package com.moodeat.domain;

import static jakarta.persistence.FetchType.*;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.moodeat.dto.Message;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRecipeRecommendation {

	@Id
	@GeneratedValue
	@Column(name = "user_recipe_recommendation_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@JdbcTypeCode(SqlTypes.JSON)
	private List<Recipe> recipes;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "character_id")
	private Character character;

	private String reason;

	@JdbcTypeCode(SqlTypes.JSON)
	private List<String> keywords;

	@JdbcTypeCode(SqlTypes.JSON)
	private List<Message> chatHistories;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	private LocalDateTime deletedAt;
}
