package afterwork.millionaire.api.oauth.service;

import afterwork.millionaire.api.oauth.dto.TokenRequest;
import afterwork.millionaire.api.oauth.dto.RevokeTokenRequest;
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

    private static final String API_URL = "https://openapivts.koreainvestment.com:29443/oauth2/tokenP";
    private static final String REVOKE_API_URL = "https://openapivts.koreainvestment.com:29443/oauth2/revokeP";

    private final WebClient webClient;

    @Autowired
    public TokenService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(API_URL).build();
    }

    /**
     * 액세스 토큰을 비동기적으로 발급받습니다.
     *
     * @param tokenRequest 토큰 발급을 위한 요청 정보
     * @return 액세스 토큰 또는 에러 응답
     */
    public Mono<ResponseEntity<Map<String, Object>>> getAccessToken(TokenRequest tokenRequest) {
        try {
            return webClient.post()
                    .uri(API_URL)
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .bodyValue(tokenRequest)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<>() {});
        } catch (Exception e) {
            log.error("외부 호출 에러: {}", e.getMessage(), e);
            return Mono.just(afterwork.millionaire.api.util.ErrorUtils.createErrorResponse("외부 호출 에러", e.getMessage()));
        }
    }

    /**
     * 토큰을 비동기적으로 취소합니다.
     *
     * @param request 토큰 취소 요청 정보
     * @return 응답
     */
    public Mono<ResponseEntity<Map<String, Object>>> revokeToken(RevokeTokenRequest request) {
        try {
            return webClient.post()
                    .uri(REVOKE_API_URL)
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .bodyValue(request)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<>() {});
        } catch (Exception e) {
            log.error("외부 호출 에러: {}", e.getMessage(), e);
            return Mono.just(afterwork.millionaire.api.util.ErrorUtils.createErrorResponse("외부 호출 에러", e.getMessage()));
        }
    }
}
