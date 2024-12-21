package com.moodeat.dto.clova;

import java.util.List;

import com.moodeat.dto.recipe.recommendation.MessageDto;

import lombok.Data;

@Data
public class ChatCompletionsDto {
	List<MessageDto> messages;
	Double temperature;
	Integer topK;
	Double topP;
	Double repeatPenalty;
	Integer maxTokens;
	Boolean includeAiFilters;
	Integer seed;
}
