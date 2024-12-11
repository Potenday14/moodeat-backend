package com.moodeat.dto.character;

import com.moodeat.dto.shared.CharacterDTO;
import lombok.Data;

import java.util.List;

@Data
public class ResponseGetCharacters {
	private List<CharacterDTO> characters;
}
