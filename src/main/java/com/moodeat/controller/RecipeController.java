package com.moodeat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moodeat.dto.ExampleErrorResponse;
import com.moodeat.dto.recipe.ResponseGetRecipeDetail;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Recipe", description = "레시피 관련 API입니다.")
@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

	@Operation(summary = "레시피 상세 정보 조회", description = "한 개의 레시피 상세 정보를 조회합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "레시피 상세 정보 조회 성공",
			content = @Content(schema = @Schema(implementation = ResponseGetRecipeDetail.class))),
		@ApiResponse(responseCode = "400", description = "잘못된 요청",
			content = @Content(schema = @Schema(implementation = ExampleErrorResponse.class)))
	})
	@GetMapping("/{recipeId}")
	public ResponseEntity<ResponseGetRecipeDetail> getRecipeDetail(
		@Parameter(description = "레시피 ID", example = "1")
		@PathVariable("recipeId") Long recipeId) {

		ResponseGetRecipeDetail response = new ResponseGetRecipeDetail();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
