package com.moodeat.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Mood {
	HAPPY("기쁨"),
	ANGRY("화남"),
	SAD("슬픔");

	private final String mood;
}
