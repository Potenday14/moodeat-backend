package com.moodeat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moodeat.dto.ResponseError;
import com.moodeat.dto.recipe.ResponseGetRecipesById;
import com.moodeat.service.RecipeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Tag(name = "Recipe", description = "레시피 관련 API입니다.")
@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {
	private final RecipeService recipeService;

	@Operation(summary = "레시피 상세 정보 조회", description = "한 개의 레시피 상세 정보를 조회합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "레시피 상세 정보 조회 성공",
			content = @Content(schema = @Schema(implementation = ResponseGetRecipesById.class))),
		@ApiResponse(responseCode = "400", description = "잘못된 요청",
			content = @Content(schema = @Schema(implementation = ResponseError.class)))
	})
	@GetMapping("/{recipeId}")
	public ResponseEntity<ResponseGetRecipesById> getRecipesByRecipeId(
		@Parameter(description = "레시피 ID", example = "1")
		@PathVariable("recipeId") Long recipeId) {

		// ResponseGetRecipesById response = new ResponseGetRecipesById();
		// ResponseGetRecipesById response = new MockupResponseGetRecipesById();
		ResponseGetRecipesById response = null;
		try {
			response = recipeService.getRecipe(recipeId);
		} catch (EntityNotFoundException e) {
			e.getStackTrace();
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
