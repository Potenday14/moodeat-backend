package com.moodeat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moodeat.dto.ResponseError;
import com.moodeat.dto.character.ResponseGetCharacters;
import com.moodeat.service.CharacterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Character", description = "캐릭터 관련 API입니다.")
@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
	private final CharacterService characterService;

	@Operation(summary = "캐릭터 리스트 조회", description = "감정 선택 창에서 보이는 캐릭터들의 정보의 리스트를 반환합니다")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "캐릭터 조회 리스트 응답 성공",
			content = @Content(mediaType = "application/json",
				schema = @Schema(implementation = ResponseGetCharacters.class))),
		@ApiResponse(responseCode = "400", description = "캐릭터 조회 리스트 응답 실패",
			content = @Content(schema = @Schema(implementation = ResponseError.class)))
	})
	@GetMapping()
	public ResponseEntity<ResponseGetCharacters> getCharacters() {
		// ResponseGetCharacters response = new ResponseGetCharacters();
		// ResponseGetCharacters response = new MockupResponseGetCharacters();
		ResponseGetCharacters response = characterService.getCharacters();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
