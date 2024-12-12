package com.moodeat.dto.mockup;

import java.time.LocalDateTime;
import java.util.List;

import com.moodeat.dto.recipe.ManualStepDto;
import com.moodeat.dto.recipe.RecipeIngredientDto;
import com.moodeat.dto.recipe.ResponseGetRecipesById;


/*
{
	"id": 1,
	"name": "돼지고기 김치찌개",
	"ingredients": {
		"main": [
				{
					"id": 1,
					"name": "쌀",
					"quantity": "30g"
				},
				{
					"id": 2,
					"name": "김치",
					"quantity": "1/2 포기"
				}
		],
		"sub": [
			{
				"name": "양념장",
				"data": [
					{
						"id": 1,
						"name": "간장",
						"quantity": "30g"
					},
					{
						{
							"id": 1,
							"name": "소금",
							"quantity": "30g"
						}
					}
				]
			}
		]
	},
	"mainPhoto": "url",
	"manuals": [
		{
			"order": 1, "photo" : "url", "description": "~~하세요."
		}
	],
	"tip": "조리 시간을 단축하려면 이렇게 해보세요!",
	"minutes": 20
	"calories": 128,
	"carbohydrates": 123,
	"protein": 12,
	"fat": 33,
	"salt": 33,
	"created_at":"",
	"updated_at":""
}
이거에 맞춰서 짜줘
 */

public class MockupResponseGetRecipesById extends ResponseGetRecipesById {
	public MockupResponseGetRecipesById() {
		setId(1L);
		setName("돼지고기 김치찌개");
		setMockupIngredients();
		setMockupManuals();
		setMainPhoto("~~~.jpg");
		setTip("조리 시간을 단축하려면 이렇게 해보세요!");
		setReason("만들기도 간단하면서 얼큰하고 칼칼한 맛이 매력적인 돼지고기 김치찌개!!");
		setMinutes(20);
		setCalories(128);
		setCarbohydrates(123);
		setProtein(12);
		setFat(33);
		setSalt(33);
		setCreatedAt(LocalDateTime.now());
		setUpdatedAt(LocalDateTime.now());

	}

	private void setMockupIngredients() {

		RecipeIngredientDto ingredient1 = new RecipeIngredientDto();
		ingredient1.setId(1L);
		ingredient1.setName("돼지고기");
		ingredient1.setQuantity("120g");

		RecipeIngredientDto ingredient2 = new RecipeIngredientDto();
		ingredient2.setId(2L);
		ingredient2.setName("김치");
		ingredient2.setQuantity("1/2 포기");

		RecipeIngredientDto ingredient3 = new RecipeIngredientDto();
		ingredient3.setId(30L);
		ingredient3.setName("간장");
		ingredient3.setQuantity("15g");

		RecipeIngredientDto ingredient4 = new RecipeIngredientDto();
		ingredient4.setId(52L);
		ingredient4.setName("고추가루");
		ingredient4.setQuantity("5g");
		List<RecipeIngredientDto> ingredients = List.of(ingredient1, ingredient2, ingredient3, ingredient4);
		setIngredients(ingredients);
	}

	private void setMockupManuals() {
		ManualStepDto manual1 = new ManualStepDto();
		manual1.setOrder(1);
		manual1.setPhoto("~1.jpg");
		manual1.setDescription("1번 하세요.");

		ManualStepDto manual2 = new ManualStepDto();
		manual2.setOrder(2);
		manual2.setPhoto("2.jpg");
		manual2.setDescription("2번 하세요.");

		List<ManualStepDto> manuals = List.of(manual1, manual2);
		setManuals(manuals);
	}

}
