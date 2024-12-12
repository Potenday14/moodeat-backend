package com.moodeat.dto.character;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResponseGetCharacters {

	@Schema(description = "캐릭터 리스트")
	private List<CharacterDto> characters;
}
