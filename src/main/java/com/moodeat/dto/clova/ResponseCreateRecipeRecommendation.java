package com.moodeat.dto.clova;

import java.util.List;

import lombok.Data;

@Data
public class ResponseCreateRecipeRecommendation {
	private List<Long> recipeIds;

	private List<String> keywords;

	private String reason;
}
