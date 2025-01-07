package afterwork.millionaire.api.oauth.service;

import afterwork.millionaire.api.oauth.dto.TokenRequest;
import afterwork.millionaire.util.WebClientUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("토큰 발급 테스트")
class TokenServiceTest {

    private HttpHeaders headers;
    private TokenRequest request;

    @BeforeEach
    void setUp() {
        // 테스트를 위한 기본 데이터 설정
        headers = new HttpHeaders();
        request = new TokenRequest();
    }

    @Test
    void getTokenSuccess() {
        // 정상적인 요청 헤더 설정
        headers.set("content-type", "application/json");

        // 요청 데이터 설정
        request.setGrant_type("client_credentials");
        request.setAppkey("");
        request.setAppsecret("");

        // 실제 API 호출을 테스트하는 부분
        Mono<ResponseEntity<Map<String, Object>>> response = WebClientUtils.sendPostRequest("oauth2/tokenP", headers, request);

        // 동기적으로 응답을 기다리고 결과를 검증
        ResponseEntity<Map<String, Object>> res = response.block();

        // 응답 상태 코드가 200 OK여야 함
        assertEquals(200, res.getStatusCode().value());
    }

    @Test
    void getToken_shouldReturnFailure() {
        // 잘못된 appkey와 appsecret을 설정하여 실패 요청을 시뮬레이션
        // 정상적인 요청 헤더 설정
        headers.set("content-type", "application/json");

        // 요청 데이터 설정
        request.setGrant_type("client_credentials");
        request.setAppkey("");
        request.setAppsecret("");

        // 잘못된 요청으로 인해 실패 응답을 받도록 설정
        Mono<ResponseEntity<Map<String, Object>>> response = WebClientUtils.sendPostRequest("uapi/token", headers, request);

        // 동기적으로 응답을 기다리고 실패 응답 검증
        ResponseEntity<Map<String, Object>> res = response.block();

        // 예를 들어, 400 Bad Request 상태 코드가 반환되면 실패
        assertEquals(500, res.getStatusCode().value());
    }
}
