package com.moodeat.dto.mockup;

import java.util.List;

import com.moodeat.dto.character.CharacterDto;
import com.moodeat.dto.character.ResponseGetCharacters;

public class MockupResponseGetCharacters extends ResponseGetCharacters {

	public MockupResponseGetCharacters() {
		CharacterDto character1 = new CharacterDto();
		character1.setId(1L);
		character1.setMood("슬픔");
		character1.setImage("https://i.imgur.com/8nLFCVP_d.webp?maxwidth=760&fidelity=grand");

		// 화남. 기쁨 으로 두개 더
		CharacterDto character2 = new CharacterDto();
		character2.setId(2L);
		character2.setMood("화남");
		character2.setImage("https://i.imgur.com/YK3I6oX_d.webp?maxwidth=760&fidelity=grand");

		CharacterDto character3 = new CharacterDto();
		character3.setId(3L);
		character3.setMood("기쁨");
		character3.setImage("https://i.imgur.com/J4qXoO6_d.webp?maxwidth=760&fidelity=grand");

		List<CharacterDto> characters = List.of(character1, character2, character3);
		setCharacters(characters);
	}
}
