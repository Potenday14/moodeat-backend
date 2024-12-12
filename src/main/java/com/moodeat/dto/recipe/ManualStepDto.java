package com.moodeat.dto.recipe;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ManualStepDto {

	@Schema(description = "순서", example = "1")
	private int order;

	@Schema(description = "사진 URL", example = "~~.jpg")
	private String photo;

	@Schema(description = "설명", example = "~~하세요.")
	private String description;
}
