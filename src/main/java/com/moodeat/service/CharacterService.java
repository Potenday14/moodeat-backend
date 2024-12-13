package com.moodeat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moodeat.domain.Character;
import com.moodeat.dto.character.CharacterDto;
import com.moodeat.dto.character.ResponseGetCharacters;
import com.moodeat.repository.character.CharacterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharacterService {
	private final CharacterRepository characterRepository;

	public ResponseGetCharacters getCharacters() {
		List<Character> characterEntityList = characterRepository.findAll();
		List<CharacterDto> characterDtoList =
			characterEntityList.stream()
				.map(item -> {
					CharacterDto dto = new CharacterDto();
					dto.setId(item.getId());
					dto.setMood(item.getMood().toString());
					dto.setImage(item.getImage());
					dto.setNickname(item.getNickname().getNickname());
					return dto;
				})
				.toList();
		ResponseGetCharacters result = new ResponseGetCharacters();
		result.setCharacters(characterDtoList);
		return result;
	}
}
