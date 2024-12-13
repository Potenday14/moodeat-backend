package com.moodeat.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Mood {
	HAPPY("Happi"),
	ANGRY("Angoori"),
	SAD("Saddi");

	private final String nickname;
}
