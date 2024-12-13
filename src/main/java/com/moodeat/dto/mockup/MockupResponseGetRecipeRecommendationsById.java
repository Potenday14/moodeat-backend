// package com.moodeat.dto.mockup;
//
// import java.util.List;
//
// import com.moodeat.dto.recipe.recommendation.RecipeRecommendationRecipeDto;
// import com.moodeat.dto.recipe.recommendation.ResponseGetRecipeRecommendationsById;
//
// public class MockupResponseGetRecipeRecommendationsById extends ResponseGetRecipeRecommendationsById {
// 	public MockupResponseGetRecipeRecommendationsById() {
// 		setReason("추우니까 따듯한 국물 드세요");
//
// 		String keyword1 = "김치";
// 		String keyword2 = "돼지고기";
// 		String keyword3 = "두부";
// 		List<String> keywords = List.of(keyword1, keyword2, keyword3);
// 		setKeywords(keywords);
//
// 		RecipeRecommendationRecipeDto recipe1 = new RecipeRecommendationRecipeDto();
// 		recipe1.setId(1L);
// 		recipe1.setName("돼지고기 김치찌개");
// 		recipe1.setMainPhoto("~~~.jpg");
// 		recipe1.setMinutes(20);
// 		recipe1.setCalories(128);
// 		RecipeRecommendationRecipeDto recipe2 = new RecipeRecommendationRecipeDto();
// 		recipe2.setId(2L);
// 		recipe2.setName("두부김치");
// 		recipe2.setMainPhoto("~~~.jpg");
// 		recipe2.setMinutes(10);
// 		recipe2.setCalories(120);
// 		RecipeRecommendationRecipeDto recipe3 = new RecipeRecommendationRecipeDto();
// 		recipe3.setId(3L);
// 		recipe3.setName("김치볶음밥");
// 		recipe3.setMainPhoto("~~~.jpg");
// 		recipe3.setMinutes(15);
// 		recipe3.setCalories(200);
// 		List<RecipeRecommendationRecipeDto> recipes = List.of(recipe1, recipe2, recipe3);
// 		setRecipes(recipes);
// 	}
// }
