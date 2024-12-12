package com.moodeat.dto.shared;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ManualDto {
	@Schema(description = "순서", example = "1")
	private int order;
	@Schema(description = "사진 URL", example = "~~.jpg")
	private String photo;
	@Schema(description = "설명", example = "1. ~~하세요.")
	private String description;
}
