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
		List<Map<String, String>> requestMessages = new ArrayList<>();

		Map<String, String> systemMessageMap = new HashMap<>();
		systemMessageMap.put("role", "system");

		// 역할 부여 및 결과 형식 안내
		StringBuilder systemContent = new StringBuilder();
		systemContent.append("당신은 사용자의 기분과 상황에 맞는 메뉴를 추천하기 위한 어시스턴트입니다.\n\n");
		systemContent.append("유저와 대화 진행중 유저가 [사용자 정보]라는 string을 포함한 메세지를 입력하면,");
		systemContent.append(" 바로 직후 응답 메세지로 '{\"recipe_ids\": number[], \"keywords\": string[],");
		systemContent.append(" \"reason\": string}' 형식의 json 객체를 반환하세요.\n\n");
		systemContent.append(" 이때, 절대 다른 설명을 포함하지 말고 json 객체만 반환하세요.\n\n");

		// 단계별 과정 안내
		systemContent.append(" 아래 순서에 따라 레시피 추천 정보를 담은 json 객체를 반환해주세요.\n\n");
		systemContent.append("1. 레시피 맵의 사이즈를 파악하세요.\n");
		systemContent.append("2. 사용자의 감정과 사용자가 선택한 재료, 대화 내용을 바탕으로, 레시피 맵에서 레시피를 추천해주세요.\n");
		systemContent.append("2-1. 레시피 맵의 사이즈가 5 이하인 경우, 모든 레시피를 추천하세요.\n");
		systemContent.append("2-2. 레시피 맵의 사이즈가 5 이상인 경우, 최소 5개 이상, 최대 8개 이하의 레시피를 추천하세요.\n");
		systemContent.append("3. 사용자의 상황과 재료에 맞는 키워드 2개를 추출하세요.\n");
		systemContent.append("4. 추천 레시피 목록에 대해 키워드를 포함한 추천 문구를 친절한 마케터처럼 작성해주세요.");
		systemContent.append(" 문구는 한글 기준 30자 이내, 반말로 작성해야 합니다.");
		systemContent.append(" 키워드에 포함되는 부분은 <b> 태그로 강조해주세요.\n");
		systemContent.append("5. 최종 반환 형식은 반드시 {\"recipe_ids\": number[], \"keywords\": string[], \"reason\": string}");
		systemContent.append(" 형태의 json 객체만을 하나의 string 타입으로 반환해야 합니다.\n\n");

		// 입출력 예시 안내
		systemContent.append("[입력값 예시]\n[사용자 정보]\n사용자의 감정: 기쁨\n재료 리스트: [새우, 두부]\n");
		systemContent.append("레시피 맵: {1=새우 두부 계란찜, 2=부추 콩가루 찜, 3=방울토마토 두부 샐러드}\n\n");
		systemContent.append("[출력값 예시]\n{\"recipe_ids\": [1, 2, 3], \"keywords\": [\"기쁨\", \"합격\"],");
		systemContent.append(" \"reason\": \"<b>기쁜 합격 소식</b>이 있는 날 맛있는 <b>콩</b>으로 만든 건강한 술 안주 어때?\"}");

		systemMessageMap.put("content", systemContent.toString());
		requestMessages.add(systemMessageMap);

		for (MessageDto message : messages) {
			Map<String, String> tmpMap = new HashMap<>();
			tmpMap.put("role", message.getRole());
			tmpMap.put("content", message.getContent());
			requestMessages.add(tmpMap);
		}

		Map<String, String> tmpMap = new HashMap<>();
		tmpMap.put("role", "user");
		tmpMap.put("content", String.format("[사용자 정보]\n사용자의 감정: %s\n재료 리스트: %s\n레시피 맵: %s", mood, ingredients, menu));
		requestMessages.add(tmpMap);

		// request 생성
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("messages", requestMessages);
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

				// 결과의 content만 파싱
				JsonNode content = objectMapper.readTree(root.path("result")
					.path("message")
					.findValuesAsText("content")
					.get(0));

				return parseJsonToResultMap(content);
			} else {
				throw new RuntimeException("Clova API 호출 실패: " + response.getStatusCode());
			}
		} catch (Exception e) {
			// default 응답 반환
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("recipe_ids", List.of(5, 6, 7, 8, 94));
			resultMap.put("keywords", List.of("기본값"));
			resultMap.put("reason", "오늘 같은 날 든든하고 속 풀리는 따듯한 <b>국물 요리</b> 어때?");

			return resultMap;
		}
	}

	Map<String, Object> parseJsonToResultMap(JsonNode node) throws Exception {
		// JSON에서 "recipe_ids", "keywords", "reason" 값을 추출하여 Map에 저장
		JsonNode recipeIdsNode = node.path("recipe_ids");
		List<Integer> recipeIds = new ArrayList<>();
		recipeIdsNode.forEach(r -> recipeIds.add(r.asInt()));

		if (recipeIds.isEmpty()) {
			throw new Exception("recipe not exists");
		}

		JsonNode keywordsNode = node.path("keywords");
		List<String> keywords = new ArrayList<>();
		keywordsNode.forEach(k -> keywords.add(k.asText()));

		if (keywords.isEmpty()) {
			throw new Exception("keywords not exists");
		}

		String reason = node.path("reason").asText();

		if (reason.isEmpty()) {
			throw new Exception("reason not exists");
		}

		// 결과를 Map에 저장
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("recipe_ids", recipeIds);
		resultMap.put("keywords", keywords);
		resultMap.put("reason", reason);

		return resultMap;
	}
}
