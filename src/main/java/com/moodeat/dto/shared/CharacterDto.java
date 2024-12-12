package com.moodeat.dto.shared;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CharacterDto {
	@Schema(description = "캐릭터 ID", example = "2")
	private Long id;

	@Schema(description = "캐릭터가 상징하는 감정", example = "슬픔")
	private String mood;

	@Schema(description = "감정 선택 시 캐릭터 이미지", example = "~~~.jpg")
	private String image;
}
