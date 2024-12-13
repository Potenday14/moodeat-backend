package com.moodeat.domain;

import com.moodeat.domain.enums.MessageRole;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
	private MessageRole role;
	private String content;
}

