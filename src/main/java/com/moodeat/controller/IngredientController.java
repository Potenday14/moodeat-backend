package com.moodeat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moodeat.dto.ResponseError;
import com.moodeat.dto.ingredient.ResponseGetIngredients;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Ingredients", description = "재료 관련 API입니다.")
@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

	@Operation(summary = "재료 리스트 조회 및 검색", description = "재료 선택 창에서의 쿼리를 통한 리스트를 반환합니다 ex) /ingredients?includes=설탕")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "재료 조회 리스트 응답 성공",
			content = @Content(mediaType = "application/json",
				schema = @Schema(implementation = ResponseGetIngredients.class))),
		@ApiResponse(responseCode = "400", description = "재료 조회 리스트 응답 실패",
			content = @Content(schema = @Schema(implementation = ResponseError.class)))
	})
	@GetMapping()
	public ResponseEntity<ResponseGetIngredients> getIngredients(
		@Parameter(description = "재료 필터링 쿼리", example = "설탕")
		@RequestParam(value = "includes", required = false) String includes
	) {
		ResponseGetIngredients response = new ResponseGetIngredients();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
