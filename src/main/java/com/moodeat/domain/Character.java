package com.moodeat.domain;

import com.moodeat.domain.enums.Mood;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Character {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "character_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(unique = true, nullable = false, length = 10)
	private Mood mood;

	@Column
	private String image;
}
