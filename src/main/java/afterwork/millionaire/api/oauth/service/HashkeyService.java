package afterwork.millionaire.api.oauth.service;

import afterwork.millionaire.api.oauth.dto.HashkeyRequest;
import afterwork.millionaire.config.ApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Consumer;

/**
 * HashkeyService
 * 외부 API를 통해 해시 키를 요청하고, 응답을 처리하는 서비스 클래스입니다.
 */
@Service
@Slf4j
public class HashkeyService {

    // 해시 키를 요청할 외부 API URL
    private static final String API_URL = "https://openapivts.koreainvestment.com:29443/uapi/hashkey";
    private final WebClient webClient;
    private final ApiProperties apiProperties;

    // 의존성 주입을 위한 생성자
    @Autowired
    public HashkeyService(WebClient.Builder webClientBuilder, ApiProperties apiProperties) {
        this.webClient = webClientBuilder.baseUrl(apiProperties.getBaseUrl()).build();
        this.apiProperties = apiProperties;
    }

    /**
     * 외부 API를 호출하여 해시 키를 요청합니다.
     *
     * @param requestDTO 해시 키 요청에 필요한 정보
     * @param headers    API 호출에 필요한 인증 헤더
     * @return 해시 키 또는 에러 응답
     */
    public Mono<ResponseEntity<Map<String, Object>>> getHashkey(HashkeyRequest requestDTO, HttpHeaders headers) {
        // WebClient를 사용하여 외부 API 호출
        return webClient.post()
                .uri(apiProperties.getHashkey())  // 요청할 URI
                .headers(httpHeaders -> {
                    // 여러 개의 헤더를 추가
                    httpHeaders.add("content-type", headers.getFirst("content-type"));
                    httpHeaders.add("appkey", headers.getFirst("appkey"));
                    httpHeaders.add("appsecret", headers.getFirst("appsecret"));
                })
                .bodyValue(requestDTO)  // requestDTO를 body에 추가 (필요한 경우)
                .retrieve()  // API 호출 및 응답 받기
                .toEntity(new ParameterizedTypeReference<Map<String, Object>>() {
                })  // 응답을 Map으로 변환
                .onErrorResume(e -> {
                    // 에러 발생 시 처리
                    log.error("외부 호출 에러: {}", e.getMessage(), e);
                    return Mono.just(afterwork.millionaire.api.util.ErrorUtils.createErrorResponse("외부 호출 에러", e.getMessage()));
                });
    }

}
