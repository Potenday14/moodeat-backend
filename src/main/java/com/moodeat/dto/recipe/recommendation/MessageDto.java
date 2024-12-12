package com.moodeat.dto.recipe.recommendation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MessageDto {

	@Schema(description = "채팅 역할 - assistant, user", example = "assistant")
	private String role;

	@Schema(description = "채팅 내용", example = "어떤 맛이 땡기니?")
	private String content;
}
