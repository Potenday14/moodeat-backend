package com.moodeat.controller;

import com.moodeat.dto.ExampleErrorResponse;
import com.moodeat.dto.recipe_recommendation.get_recipe_recommendations.ResponseGetRecipeRecommendations;
import com.moodeat.dto.recipe_recommendation.recommend_recipes.RequestRecommendRecipes;
import com.moodeat.dto.recipe_recommendation.recommend_recipes.ResponseRecommendRecipes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Recipe Recommendation", description = "레시피 추천 관련 API입니다.")
@RestController
@RequestMapping("/recipe-recommendations")
@RequiredArgsConstructor
public class RecipeRecommendationController {


	@Operation(summary = "레시피 추천 요청", description = "감정, 재료, 채팅기록을 통해 레시피 추천을 요청합니다. 이후 추천 리스트를 확인할 수 있는 id를 반환합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "추천 레시피 생성 성공",
			content = @Content(mediaType = "application/json",
				schema = @Schema(implementation = ResponseRecommendRecipes.class))),
		@ApiResponse(responseCode = "400", description = "추천 레시피 생성 실패",
			content = @Content(schema = @Schema(implementation = ExampleErrorResponse.class)))
	})
	@PostMapping()
	public ResponseEntity<ResponseRecommendRecipes> recommendRecipes(
		@Valid @RequestBody RequestRecommendRecipes request
	) {

		ResponseRecommendRecipes response = new ResponseRecommendRecipes();

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}


	@Operation(summary = "레시피 추천 요청", description = "감정, 재료, 채팅기록을 통해 레시피 추천을 요청합니다. 이후 추천 리스트를 확인할 수 있는 id를 반환합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "추천 레시피 생성 성공",
			content = @Content(mediaType = "application/json",
			schema = @Schema(implementation = ResponseGetRecipeRecommendations.class))),
		@ApiResponse(responseCode = "400", description = "추천 레시피 생성 실패",
			content = @Content(schema = @Schema(implementation = ExampleErrorResponse.class)))
	})
	@GetMapping("/{recommendationId}")
	public ResponseEntity<ResponseGetRecipeRecommendations> getRecipeRecommendations(
		@Parameter(description = "레시피 ID", example = "1")
		@PathVariable("recommendationId") Long recommendationId
	) {

		ResponseGetRecipeRecommendations response = new ResponseGetRecipeRecommendations();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
