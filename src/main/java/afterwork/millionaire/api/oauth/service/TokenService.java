package afterwork.millionaire.api.oauth.service;

import afterwork.millionaire.api.oauth.dto.TokenRequest;
import afterwork.millionaire.api.oauth.dto.RevokeTokenRequest;
import afterwork.millionaire.config.ApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * TokenServices
 * OAuth2 토큰을 발급받기 위한 서비스입니다.
 * 주어진 요청 정보로 액세스 토큰을 발급받아 반환합니다.
 */
@Service
@Slf4j
public class TokenService {

    private final WebClient webClient;
    private final ApiProperties apiProperties;

    // 생성자: WebClient와 ApiProperties를 의존성 주입
    @Autowired
    public TokenService(WebClient.Builder webClientBuilder, ApiProperties apiProperties) {
        this.webClient = webClientBuilder.baseUrl(apiProperties.getBaseUrl()).build();
        this.apiProperties = apiProperties;
    }

    /**
     * 액세스 토큰을 비동기적으로 발급받습니다.
     *
     * @param tokenRequest 토큰 발급을 위한 요청 정보
     * @return 액세스 토큰 또는 에러 응답
     */
    public Mono<ResponseEntity<Map<String, Object>>> getAccessToken(TokenRequest tokenRequest) {
        // 액세스 토큰 발급 요청
        return webClient.post()
                .uri(apiProperties.getToken())  // 토큰 발급 URI
                .header("Content-Type", "application/json; charset=UTF-8")  // 요청 헤더 설정
                .bodyValue(tokenRequest)  // 요청 본문 설정
                .retrieve()
                .toEntity(new ParameterizedTypeReference<Map<String, Object>>() {})  // 응답을 Map으로 변환
                .onErrorResume(e -> {
                    // 에러 발생 시 처리
                    log.error("외부 호출 에러: {}", e.getMessage(), e);
                    return Mono.just(afterwork.millionaire.api.util.ErrorUtils.createErrorResponse("외부 호출 에러", e.getMessage()));
                });
    }

    /**
     * 토큰을 비동기적으로 취소합니다.
     *
     * @param request 토큰 취소 요청 정보
     * @return 응답
     */
    public Mono<ResponseEntity<Map<String, Object>>> revokeToken(RevokeTokenRequest request) {
        // 토큰 취소 요청
        return webClient.post()
                .uri(apiProperties.getRevoke())  // 토큰 취소 URI
                .header("Content-Type", "application/json; charset=UTF-8")  // 요청 헤더 설정
                .bodyValue(request)  // 요청 본문 설정
                .retrieve()
                .toEntity(new ParameterizedTypeReference<Map<String, Object>>() {})  // 응답을 Map으로 변환
                .onErrorResume(e -> {
                    // 에러 발생 시 처리
                    log.error("외부 호출 에러: {}", e.getMessage(), e);
                    return Mono.just(afterwork.millionaire.api.util.ErrorUtils.createErrorResponse("외부 호출 에러", e.getMessage()));
                });
    }
}
