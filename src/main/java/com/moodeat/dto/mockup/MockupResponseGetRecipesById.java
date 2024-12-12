package com.moodeat.dto.mockup;

import java.time.LocalDateTime;
import java.util.List;

import com.moodeat.dto.recipe.ManualStepDto;
import com.moodeat.dto.recipe.RecipeIngredientDto;
import com.moodeat.dto.recipe.RecipeIngredientsDto;
import com.moodeat.dto.recipe.RecipeSubIngredientDto;
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

		RecipeIngredientsDto ingredients = new RecipeIngredientsDto();

		RecipeIngredientDto main1 = new RecipeIngredientDto();
		main1.setId(1L);
		main1.setName("돼지고기");
		main1.setQuantity("120g");

		RecipeIngredientDto main2 = new RecipeIngredientDto();
		main2.setId(2L);
		main2.setName("김치");
		main2.setQuantity("1/2 포기");

		List<RecipeIngredientDto> mainList = List.of(main1, main2);
		ingredients.setMain(mainList);

		RecipeSubIngredientDto sub1 = new RecipeSubIngredientDto();
		sub1.setName("양념장");
		RecipeIngredientDto sub1Data1 = new RecipeIngredientDto();
		sub1Data1.setId(30L);
		sub1Data1.setName("간장");
		sub1Data1.setQuantity("15g");

		RecipeIngredientDto sub1Data2 = new RecipeIngredientDto();
		sub1Data2.setId(52L);
		sub1Data2.setName("고추가루");
		sub1Data2.setQuantity("5g");

		sub1.setData(List.of(sub1Data1, sub1Data2));

		List<RecipeSubIngredientDto> subList = List.of(sub1);
		ingredients.setSub(subList);

		setIngredients(ingredients);
	}

	/*
	  "manuals": [
    {
      "order": 1,
      "photo": "~~.jpg",
      "description": "~~하세요."
    }
  ],
	*/
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
