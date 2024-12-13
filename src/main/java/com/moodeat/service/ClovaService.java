package com.moodeat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moodeat.dto.recipe.recommendation.MessageDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClovaService {

	@Value("${clova.url}")
	private String url;
	@Value("${clova.api-key}")
	private String apiKey;
	@Value("${clova.apigw-api-key}")
	private String apigwKey;
	@Value("${clova.request-id}")
	private String requestId;

	public Map<String, Object> createRecipe(List<MessageDto> messages, String mood, Map<Long, String> menu,
		List<String> ingredients) {
		// RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate();

		// 메세지 입력
		Map<String, String> systemMessageMap = new HashMap<>();
		systemMessageMap.put("role", "system");
		systemMessageMap.put("content",
			"당신은 사용자의 기분과 상황에 맞는 메뉴를 추천하기 위한 어시스턴트입니다."
				+ " 아래 순서를 바탕으로 레시피 추천 정보를 담은 json 객체를 반환해주세요. \n"
				+ "1. 사용자의 감정과 사용자가 선택한 재료, 대화 내용을 바탕으로, 레시피 리스트에서 레시피를 추천해주세요."
				+ " (레시피 리스트의 개수가 5개 이하인 경우 반드시 모든 레시피 추천, "
				+ " 레시피 리스트 개수가 5개 이상인 경우 반드시 최소 5개 이상, 최대 7개 이하 레시피 추천"
				+ "2. 사용자가 선택한 재료, 상황에 맞는 키워드를 2개 생성해주세요. \n"
				+ "3. 마지막으로 키워드를 포함한 추천 문구를 친절한 마케터처럼 작성해주세요. (한글 기준 30자 이내)"
				+ " 키워드에 포함되는 부분은 <b> 태그로 강조해주세요.\n"
				+ "반환 형식은 반드시 지정된 형식의 json 객체여야 합니다.\n\n"
				+ "[입력값 예시]\n사용자의 감정: 기쁨\n재료 리스트: [새우, 두부]\n"
				+ "레시피 리스트: {1=새우 두부 계란찜, 2=부추 콩가루 찜, 3=방울토마토 두부 샐러드}\n\n"
				+ "[출력값 예시(동일 형식의 json 객체만 반환)]\n{\"recipe_ids\": [1, 3], \"keywords\": [기쁨, 합격], "
				+ "\"reason\": \"<b>기쁜 합격 소식</b>이 있는 날 맛있는  <b>두부</b>로 만든 술 안주 어때요?\"}\n\n");

		Map<String, String> userMessageMap = new HashMap<>();
		for (MessageDto message : messages) {
			userMessageMap.put("role", message.getRole());
			userMessageMap.put("content", message.getContent());
		}

		userMessageMap.put("role", "user");
		userMessageMap.put("content", String.format("사용자의 감정: %s\n재료 리스트: %s\n레시피 리스트: %s", mood, ingredients, menu));

		System.out.println(systemMessageMap);
		System.out.println(userMessageMap);
		// request 생성
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("messages", new Map[] {systemMessageMap, userMessageMap});
		requestBody.put("topP", 0.8);
		requestBody.put("topK", 0);
		requestBody.put("maxTokens", 400);
		requestBody.put("temperature", 0.7);
		requestBody.put("repeatPenalty", 3.5);
		requestBody.put("includeAiFilters", true);
		requestBody.put("seed", 0);

		// HTTP 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-NCP-CLOVASTUDIO-API-KEY", apiKey);
		headers.set("X-NCP-APIGW-API-KEY", apigwKey);
		headers.set("X-NCP-CLOVASTUDIO-REQUEST-ID", requestId);
		headers.setContentType(MediaType.APPLICATION_JSON);

		// 요청 엔티티 생성
		HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

		// API 호출
		try {
			ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

			// 성공 응답 반환
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode root = objectMapper.readTree(response.getBody());

				System.out.println(root);
				// content만 파싱
				JsonNode result = objectMapper.readTree(root.path("result")
					.path("message")
					.findValuesAsText("content")
					.get(0));

				// JSON에서 "recipe_ids", "keywords", "reason" 값을 추출하여 Map에 저장
				JsonNode recipeIdsNode = result.path("recipe_ids");
				List<Integer> recipeIds = new ArrayList<>();
				recipeIdsNode.forEach(node -> recipeIds.add(node.asInt()));

				JsonNode keywordsNode = result.path("keywords");
				List<String> keywords = new ArrayList<>();
				keywordsNode.forEach(node -> keywords.add(node.asText()));

				String reason = result.path("reason").asText();

				// 결과를 Map에 저장
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("recipe_ids", recipeIds);
				resultMap.put("keywords", keywords);
				resultMap.put("reason", reason);

				return resultMap;
			} else {
				throw new RuntimeException("Clova API 호출 실패: " + response.getStatusCode());
			}
		} catch (Exception e) {
			throw new RuntimeException("Clova API 호출 중 오류 발생", e);
		}
	}
}
