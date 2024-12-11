package com.moodeat.controller;


import com.moodeat.dto.character.ResponseGetCharacter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CHARACTER", description = "캐릭터 관련 API입니다.")
@RestController
@RequestMapping("/character")
@RequiredArgsConstructor
public class CharacterController {

	@GetMapping()
	public ResponseEntity<ResponseGetCharacter> getCharacter (){

		ResponseGetCharacter response = new ResponseGetCharacter();

		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

}
