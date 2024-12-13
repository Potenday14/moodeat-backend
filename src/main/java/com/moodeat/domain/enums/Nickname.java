package com.moodeat.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Nickname {
	HAPPI("Happi"),
	ANGOORI("Angoori"),
	SADDI("Saddi");

	private final String nickname;
}
