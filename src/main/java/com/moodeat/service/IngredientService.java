package com.moodeat.service;

import org.springframework.stereotype.Service;

import com.moodeat.repository.ingredient.IngredientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientService {
	private final IngredientRepository ingredientRepository;
}
