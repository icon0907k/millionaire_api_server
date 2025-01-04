package afterwork.millionaire.api.oauth.service;

import afterwork.millionaire.api.oauth.dto.HashkeyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * HashkeyServiceTest
 * HashkeyService의 동작을 검증하기 위한 테스트 클래스.
 * RestTemplate을 모킹하여 실제 HTTP 요청 없이 서비스의 로직을 테스트합니다.
 */
@DisplayName("HashkeyService 테스트")
public class HashkeyServiceTest {

    @InjectMocks
    private HashkeyService hashkeyService; // 테스트 대상 서비스

    @Mock
    private RestTemplate restTemplate; // 모킹된 RestTemplate

    private HashkeyRequest hashkeyRequest; // 테스트 요청 객체
    private HttpHeaders headers; // 테스트용 HTTP 헤더

    @BeforeEach
    public void setUp() {
        // Mockito 초기화
        MockitoAnnotations.openMocks(this);

        // 요청 객체 및 헤더 초기화
        hashkeyRequest = new HashkeyRequest();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("appkey", "키값 셋팅 바람");
        headers.set("appsecret", "키값 셋팅 바람");

        // 성공 응답 모킹
        ResponseEntity<Map<String, Object>> mockeResponse =
                new ResponseEntity<>(Map.of("key", "value"), HttpStatus.OK);

        when(restTemplate.exchange(
                any(String.class),         // URL
                any(HttpMethod.class),     // HTTP 메서드
                any(HttpEntity.class),     // HTTP 요청
                any(new ParameterizedTypeReference<Map<String, Object>>() {}.getClass()) // 응답 타입
        )).thenReturn(mockeResponse);
    }

    @Test
    @DisplayName("Hashkey 발급 성공 테스트")
    public void testGetHashkey_Success() {
        // 성공 시나리오: CANO 설정
        hashkeyRequest.setCANO("50122221");

        // 서비스 호출
        ResponseEntity<Map<String, Object>> response = hashkeyService.getHashkey(hashkeyRequest, headers);

        // 응답 검증
        assertEquals(HttpStatus.OK, response.getStatusCode(), "HTTP 상태 코드가 OK여야 합니다.");
    }

    @Test
    @DisplayName("Hashkey 발급 실패 테스트")
    public void testGetHashkey_Failure() {
        // 실패 시나리오: appsecret을 빈 값으로 설정
        headers.set("appsecret", "");

        // 실패 응답 모킹
        ResponseEntity<Map<String, Object>> mockeResponse =
                new ResponseEntity<>(Map.of("status", "error"), HttpStatus.BAD_REQUEST);

        when(restTemplate.exchange(
                any(String.class),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(new ParameterizedTypeReference<Map<String, Object>>() {}.getClass())
        )).thenReturn(mockeResponse);

        // 서비스 호출
        ResponseEntity<Map<String, Object>> response = hashkeyService.getHashkey(hashkeyRequest, headers);

        // 응답 검증
        assertEquals("error", Objects.requireNonNull(response.getBody()).get("status"), "응답 상태가 'error'여야 합니다.");
    }
}
