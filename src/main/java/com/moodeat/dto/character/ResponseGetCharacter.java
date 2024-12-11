package com.moodeat.dto.character;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResponseGetCharacter {
	@Schema(description = "캐릭터 ID" , example = "2")
	private Long id;

	@Schema(description = "캐릭터 ID" , example = "2")
	private String mood;

	@Schema(description = "캐릭터 ID" , example = "2")
	private String image;
}
