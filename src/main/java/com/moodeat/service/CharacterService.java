package com.moodeat.service;

import org.springframework.stereotype.Service;

import com.moodeat.repository.character.CharacterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharacterService {
	private final CharacterRepository characterRepository;
}
