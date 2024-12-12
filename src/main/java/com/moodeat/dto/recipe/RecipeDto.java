package com.moodeat.dto.recipe;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {

	@Schema(description = "레시피 ID", example = "1")
	private Long id;

	@Schema(description = "레시피 이름", example = "돼지고기 김치찌개")
	private String name;

	@Schema(description = "레시피 재료")
	private RecipeIngredientsDto ingredients;

	@Schema(description = "레시피 대표 사진", example = "~~~.jpg")
	private String mainPhoto;

	@Schema(description = "레시피 조리 설명")
	private List<ManualStepDto> manuals;

	@Schema(description = "레시피 팁", example = "조리 시간을 단축하려면 이렇게 해보세요!")
	private String tip;

	@Schema(description = "레시피 조리 시간(분)", example = "20")
	private int minutes;

	@Schema(description = "레시피 칼로리", example = "128")
	private int calories;

	@Schema(description = "레시피 탄수화물", example = "123")
	private int carbohydrates;

	@Schema(description = "레시피 단백질", example = "12")
	private int protein;

	@Schema(description = "레시피 지방", example = "33")
	private int fat;

	@Schema(description = "레시피 나트륨", example = "33")
	private int salt;

	@Schema(description = "생성일", example = "2021-08-01")
	private LocalDateTime createdAt;

	@Schema(description = "수정일", example = "2021-08-01")
	private LocalDateTime updatedAt;
}
