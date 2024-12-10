package com.moodeat.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
public class Ingredient {

	@Id
	@GeneratedValue
	@Column(name = "ingredient_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;
}
