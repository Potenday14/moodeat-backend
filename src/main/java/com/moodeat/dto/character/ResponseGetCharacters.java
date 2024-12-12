package com.moodeat.dto.character;

import java.util.List;

import com.moodeat.dto.shared.CharacterDto;

import lombok.Data;

@Data
public class ResponseGetCharacters {
	private List<CharacterDto> characters;
}
