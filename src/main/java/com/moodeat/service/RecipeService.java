package com.moodeat.service;

import org.springframework.stereotype.Service;

import com.moodeat.repository.recipe.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeService {
	private final RecipeRepository recipeRepository;
}
