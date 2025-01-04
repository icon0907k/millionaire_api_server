package afterwork.millionaire.api.oauth.service;

import afterwork.millionaire.api.oauth.dto.TokenRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.Map;

/**
 * TokenServices
 * OAuth2 토큰을 발급받기 위한 서비스입니다.
 * 주어진 요청 정보로 액세스 토큰을 발급받아 반환합니다.
 */
@Service
@Slf4j
public class TokenService {
    // 액세스 토큰을 요청할 API URL
    private static final String API_URL = "https://openapivts.koreainvestment.com:29443/oauth2/tokenP";

    /**
     * 액세스 토큰을 발급받습니다.
     *
     * @param tokenRequest 토큰 발급을 위한 요청 정보
     * @return 액세스 토큰 또는 에러 응답
     */
    public ResponseEntity<Map<String, Object>> getAccessToken(TokenRequest tokenRequest) {
        try {
            // RestTemplate을 사용해 HTTP 요청을 보냄
            RestTemplate restTemplate = new RestTemplate();

            // HTTP 헤더 설정 (Content-Type: application/json)
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type","application/json; charset=UTF-8");

            // 요청 본문과 헤더를 포함한 HTTP Entity 생성
            HttpEntity<TokenRequest> request = new HttpEntity<>(tokenRequest, headers);

            // API 호출 및 응답 처리
            return restTemplate.exchange(
                    API_URL,             // 요청할 API URL
                    HttpMethod.POST,     // HTTP 메서드 (POST)
                    request,             // 요청 본문과 헤더 포함
                    new ParameterizedTypeReference<>() {} // 응답 타입 설정
            );
        } catch (Exception e) {
            // 예외 발생 시 에러 로그 출력 및 에러 응답 반환
            log.error("외부 호출 에러: {}", e.getMessage(), e);
            return afterwork.millionaire.api.util.ErrorUtils.createErrorResponse("외부 호출 에러", e.getMessage());
        }
    }
}
