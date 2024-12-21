package com.moodeat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moodeat.domain.Character;
import com.moodeat.domain.Recipe;
import com.moodeat.domain.RecipeRecommendation;
import com.moodeat.domain.UserRecipeRecommendation;
import com.moodeat.domain.enums.Mood;
import com.moodeat.dto.ResponseError;
import com.moodeat.dto.clova.RequestCreateRecipeRecommendation;
import com.moodeat.dto.clova.ResponseCreateRecipeRecommendation;
import com.moodeat.dto.ingredient.IngredientDto;
import com.moodeat.dto.recipe.recommendation.MessageDto;
import com.moodeat.dto.recipe.recommendation.RecipeRecommendationRecipeDto;
import com.moodeat.dto.recipe.recommendation.RequestPostRecipeRecommendations;
import com.moodeat.dto.recipe.recommendation.ResponseGetRecipeRecommendationsById;
import com.moodeat.dto.recipe.recommendation.ResponsePostRecipeRecommendations;
import com.moodeat.service.CharacterService;
import com.moodeat.service.ClovaService;
import com.moodeat.service.RecipeRecommendationService;
import com.moodeat.service.RecipeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Recipe Recommendation", description = "레시피 추천 관련 API입니다.")
@RestController
@RequestMapping("/recipe-recommendations")
@RequiredArgsConstructor
public class RecipeRecommendationController {

	private final RecipeRecommendationService recipeRecommendationService;
	private final CharacterService characterService;
	private final RecipeService recipeService;
	private final ClovaService clovaService;

	@Operation(summary = "추천 레시피 생성 요청", description = "감정, 재료, 채팅기록을 통해 레시피 추천을 요청합니다. 이후 추천 리스트를 확인할 수 있는 id를 반환합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "추천 레시피 생성 성공",
			content = @Content(mediaType = "application/json",
				schema = @Schema(implementation = ResponsePostRecipeRecommendations.class))),
		@ApiResponse(responseCode = "400", description = "추천 레시피 생성 실패",
			content = @Content(schema = @Schema(implementation = ResponseError.class)))
	})
	@PostMapping()
	public ResponseEntity<ResponsePostRecipeRecommendations> postRecipeRecommendations(
		@Valid @RequestBody RequestPostRecipeRecommendations request
	) {

		// 1. 무드 조회
		Long characterId = request.getCharacterId();
		Character character = characterService.getCharacterById(characterId);
		Mood mood = character.getMood();

		// 2. 레시피 ID와 이름 추출
		// (1) 재료로 레시피 필터링 후 가져오기
		List<Long> ingredientIds = request.getIngredients().stream().map(IngredientDto::getId).toList();
		List<Recipe> recipes = recipeService.getRecipesByIngredientIds(ingredientIds);

		// (2) 맵에 저장
		Map<Long, String> recipeIdAndNames = new HashMap<>();
		for (Recipe recipe : recipes) {
			recipeIdAndNames.put(recipe.getId(), recipe.getName());
		}

		// 3. 재료명 추출
		List<IngredientDto> ingredients = request.getIngredients();
		List<String> ingredientNames = new ArrayList<>();
		for (IngredientDto ingredient : ingredients) {
			ingredientNames.add(ingredient.getName());
		}

		// 4. 채팅 내역 가져오기
		List<MessageDto> chatHistories = request.getChatHistories();

		RequestCreateRecipeRecommendation dto = new RequestCreateRecipeRecommendation();
		dto.setMood(mood);
		dto.setRecipeIdAndNames(recipeIdAndNames);
		dto.setIngredientNames(ingredientNames);
		dto.setChatHistories(chatHistories);

		// 무드, 레시피 이름, 재료, 채팅 내역 기반으로 추천 레시피 생성
		ResponseCreateRecipeRecommendation result = clovaService.createRecipeRecommendation(dto);

		// 생성된 레시피 저장
		Long id = recipeRecommendationService.saveRecipeRecommendation(
			result.getRecipeIds(),
			character,
			result.getReason(),
			result.getKeywords(),
			request.getChatHistories()
		);

		ResponsePostRecipeRecommendations response = new ResponsePostRecipeRecommendations();
		response.setRecommendationId(id);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "추천 레시피 리스트 조회", description = "추천 레시피 ID로 추천 레시피 정보를 조회합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "추천 레시피 조회 성공",
			content = @Content(mediaType = "application/json",
				schema = @Schema(implementation = ResponseGetRecipeRecommendationsById.class))),
		@ApiResponse(responseCode = "400", description = "추천 레시피 조회 실패",
			content = @Content(schema = @Schema(implementation = ResponseError.class)))
	})
	@GetMapping("/{recommendationId}")
	public ResponseEntity<ResponseGetRecipeRecommendationsById> getRecipeRecommendationsById(
		@Parameter(description = "레시피 ID", example = "1")
		@PathVariable("recommendationId") Long recommendationId) {

		UserRecipeRecommendation userRecipeRecommendation
			= recipeRecommendationService.getRecipeRecommendationById(recommendationId).get();

		List<Recipe> recipes = userRecipeRecommendation.getRecipes().stream()
			.map(RecipeRecommendation::getRecipe).toList();

		ResponseGetRecipeRecommendationsById response =
			ResponseGetRecipeRecommendationsById.builder()
				.reason(userRecipeRecommendation.getReason())
				.keywords(userRecipeRecommendation.getKeywords())
				.recipes(recipes.stream().map(r -> RecipeRecommendationRecipeDto.builder()
					.id(r.getId())
					.name(r.getName())
					.mainPhoto(r.getMainPhoto())
					.minutes(r.getMinutes())
					.calories(r.getCalories())
					.build()
				).toList())
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
