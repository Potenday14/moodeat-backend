package com.moodeat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moodeat.dto.clova.ChatCompletionsDto;
import com.moodeat.dto.clova.RequestCreateRecipeRecommendation;
import com.moodeat.dto.clova.ResponseCreateRecipeRecommendation;
import com.moodeat.dto.recipe.recommendation.MessageDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

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

	public ResponseCreateRecipeRecommendation createRecipeRecommendation(RequestCreateRecipeRecommendation request) {
		ChatCompletionsDto requestBody = createChatCompletionsDto(request);

		WebClient webClient = WebClient.builder()
			.baseUrl(url)
			.defaultHeaders(headers -> {
				headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
				headers.add("X-NCP-CLOVASTUDIO-API-KEY", apiKey);
				headers.add("X-NCP-APIGW-API-KEY", apigwKey);
				headers.add("X-NCP-CLOVASTUDIO-REQUEST-ID", requestId);
			})
			.build();

		return webClient.post()
			.bodyValue(requestBody)
			.retrieve()
			.toEntity(String.class)
			.map(response -> {
				try {
					return parseJsonToDto(response.getBody());
				} catch (Exception e) {
					return createDefaultResponse();
				}
			})
			.onErrorResume(e -> Mono.just(createDefaultResponse()))
			.block();
	}

	private ChatCompletionsDto createChatCompletionsDto(RequestCreateRecipeRecommendation request) {
		List<MessageDto> messages = new ArrayList<>();

		// 시스템 메세지
		MessageDto systemMessage = new MessageDto();
		systemMessage.setRole("system");

		// (1) 역할 부여 및 결과 형식 안내
		StringBuilder systemContent = new StringBuilder();
		systemContent.append("당신은 사용자의 기분과 상황에 맞는 메뉴를 추천하기 위한 어시스턴트입니다.\n\n");
		systemContent.append("유저와 대화 진행중 유저가 [사용자 정보]라는 string을 포함한 메세지를 입력하면,");
		systemContent.append(" 바로 직후 응답 메세지로 '{\"recipeIds\": number[], \"keywords\": string[],");
		systemContent.append(" \"reason\": string}' 형식의 json 객체를 반환하세요.\n\n");
		systemContent.append(" 이때, 절대 다른 설명을 포함하지 말고 json 객체만 반환하세요.\n\n");

		// (2) 단계별 과정 안내
		systemContent.append(" 아래 순서에 따라 레시피 추천 정보를 담은 json 객체를 반환해주세요.\n\n");
		systemContent.append("1. 레시피 맵의 사이즈를 파악하세요.\n");
		systemContent.append("2. 사용자의 감정과 사용자가 선택한 재료, 대화 내용을 바탕으로, 레시피 맵에서 레시피를 추천해주세요.\n");
		systemContent.append("2-1. 레시피 맵의 사이즈가 5 이하인 경우, 모든 레시피를 추천하세요.\n");
		systemContent.append("2-2. 레시피 맵의 사이즈가 5 이상인 경우, 최소 5개 이상, 최대 8개 이하의 레시피를 추천하세요.\n");
		systemContent.append("3. 사용자의 상황과 재료에 맞는 키워드 2개를 추출하세요.\n");
		systemContent.append("4. 추천 레시피 목록에 대해 키워드를 포함한 추천 문구를 친절한 마케터처럼 작성해주세요.");
		systemContent.append(" 문구는 한글 기준 30자 이내, 반말로 작성해야 합니다.");
		systemContent.append(" 키워드에 포함되는 부분은 <b> 태그로 강조해주세요.\n");
		systemContent.append("5. 최종 반환 형식은 반드시 {\"recipeIds\": number[], \"keywords\": string[], \"reason\": string}");
		systemContent.append(" 형태의 json 객체만을 하나의 string 타입으로 반환해야 합니다.\n\n");

		// (3) 입출력 예시 안내
		systemContent.append("[입력값 예시]\n[사용자 정보]\n사용자의 감정: 기쁨\n재료 리스트: [새우, 두부]\n");
		systemContent.append("레시피 맵: {1=새우 두부 계란찜, 2=부추 콩가루 찜, 3=방울토마토 두부 샐러드}\n\n");
		systemContent.append("[출력값 예시]\n{\"recipeIds\": [1, 2, 3], \"keywords\": [\"기쁨\", \"합격\"],");
		systemContent.append(" \"reason\": \"<b>기쁜 합격 소식</b>이 있는 날 맛있는 <b>콩</b>으로 만든 건강한 술 안주 어때?\"}");

		systemMessage.setContent(systemContent.toString());
		messages.add(systemMessage);

		// 유저 메세지
		for (MessageDto message : request.getChatHistories()) {
			MessageDto userMessage = new MessageDto();
			userMessage.setRole("user");
			userMessage.setContent(message.getContent());
			messages.add(userMessage);
		}

		// 사용자 정보 메세지
		MessageDto userInfoMessage = new MessageDto();
		userInfoMessage.setRole("user");
		userInfoMessage.setContent(String.format("[사용자 정보]\n사용자의 감정: %s\n재료 리스트: %s\n레시피 맵: %s",
			request.getMood().getMood(), request.getIngredientNames(), request.getRecipeIdAndNames()));
		messages.add(userInfoMessage);

		ChatCompletionsDto dto = new ChatCompletionsDto();
		dto.setMessages(messages);
		dto.setTemperature(0.7);
		dto.setTopK(0);
		dto.setTopP(0.8);
		dto.setRepeatPenalty(3.5);
		dto.setMaxTokens(400);
		dto.setIncludeAiFilters(true);
		dto.setSeed(0);

		return dto;
	}

	private ResponseCreateRecipeRecommendation parseJsonToDto(String responseBody) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(responseBody);

		// responseBody의 content 필드만 파싱
		JsonNode content = objectMapper.readTree(root.path("result")
			.path("message")
			.findValuesAsText("content")
			.getFirst());

		// recipeIds 필드 추출
		List<Long> recipeIds = new ArrayList<>();

		JsonNode recipeIdsNode = content.path("recipeIds");
		recipeIdsNode.forEach(recipeId -> recipeIds.add(recipeId.asLong()));

		if (recipeIds.isEmpty()) {
			throw new Exception("recipe not exists");
		}

		// keywords 필드 추출
		List<String> keywords = new ArrayList<>();

		JsonNode keywordsNode = content.path("keywords");
		keywordsNode.forEach(keyword -> keywords.add(keyword.asText()));

		if (keywords.isEmpty()) {
			throw new Exception("keywords not exists");
		}

		// reason 필드 추출
		String reason = content.path("reason").asText();

		if (reason.isEmpty()) {
			throw new Exception("reason not exists");
		}

		// 결과를 dto로 변환하여 반환
		ResponseCreateRecipeRecommendation response = new ResponseCreateRecipeRecommendation();
		response.setRecipeIds(recipeIds);
		response.setKeywords(keywords);
		response.setReason(reason);
		return response;
	}

	private ResponseCreateRecipeRecommendation createDefaultResponse() {
		ResponseCreateRecipeRecommendation dto = new ResponseCreateRecipeRecommendation();
		dto.setRecipeIds(Stream.of(5, 6, 7, 8, 94).map(Integer::longValue).toList());
		dto.setKeywords(List.of("기본값"));
		dto.setReason("오늘 같은 날 든든하고 속 풀리는 따듯한 <b>국물 요리</b> 어때?");
		return dto;
	}
}
