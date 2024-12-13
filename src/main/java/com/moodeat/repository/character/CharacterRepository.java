package com.moodeat.repository.character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moodeat.domain.Character;

@Repository
public interface CharacterRepository
	extends JpaRepository<Character, Long> {
}
