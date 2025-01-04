package afterwork.millionaire.api.oauth.service;

import afterwork.millionaire.api.oauth.dto.KeyRequest;
import afterwork.millionaire.api.oauth.dto.HashkeyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class HashkeyServiceTest {

    // HashkeyService에 Mock된 RestTemplate을 주입하기 위한 어노테이션
    @InjectMocks
    private HashkeyService hashkeyService;  // 모킹된 RestTemplate을 서비스에 주입

    // RestTemplate을 모킹하기 위한 어노테이션
    @Mock
    private RestTemplate restTemplate;  // RestTemplate을 모킹

    // 테스트에 사용할 요청 객체들
    private HashkeyRequest hashkeyRequest;
    private KeyRequest keyRequest;

    @BeforeEach
    public void setUp() {
        // 모킹 초기화 (Mockito 초기화)
        MockitoAnnotations.initMocks(this);

        // 테스트 데이터 초기화
        hashkeyRequest = new HashkeyRequest();
        keyRequest = new KeyRequest();
        keyRequest.setContentType("application/json; charset=utf-8");
        keyRequest.setAppKey("키값 셋팅 바람");  // 테스트용 AppKey 설정
        keyRequest.setAppsecret("키값 셋팅 바람");  // 테스트용 AppSecret 설정

        // RestTemplate의 동작을 모킹
        // ResponseEntity 객체는 HTTP 응답을 나타내는 객체
        // 여기서 mockResponse는 RestTemplate의 exchange 메서드가 반환할 가짜 응답을 정의
        // "key"와 "value"를 포함한 Map을 응답 본문으로 설정하고, 응답 상태는 HttpStatus.OK(200 OK)로 설정
        // 이를 통해 실제 HTTP 요청을 보내지 않고, 예상되는 응답을 반환
        ResponseEntity<Map<String, Object>> mockResponse = new ResponseEntity<>(Map.of("key", "value"), HttpStatus.OK);

        // RestTemplate의 exchange 메서드 호출 시 위에서 설정한 mockResponse를 반환하도록 모킹
        // when()은 주어진 조건에 대해 메서드가 호출될 때 무엇을 반환할지 설정
        // 여기서는 restTemplate.exchange 메서드가 호출될 때 mockResponse를 반환하도록 설정
        // any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class) 는
        // exchange 메서드의 인자들을 모두 와일드카드로 받아들이는 설정 즉 어떤 값이 들어와도 상관없이
        // mockResponse를 반환하도록 설정하는 것
        when(restTemplate.exchange(
                any(String.class),  // 첫 번째 인자: URL (String 타입)
                any(HttpMethod.class),  // 두 번째 인자: HTTP 메서드 (HttpMethod 타입)
                any(HttpEntity.class),  // 세 번째 인자: HTTP 요청 본문 (HttpEntity 타입)
                any(Class.class)  // 네 번째 인자: 응답 본문 타입 (Class 타입)
        )).thenReturn(mockResponse);  // mockResponse를 반환하도록 설정

    }

    @Test
    public void testGetHashkey_Success() {
        // 테스트용 해시키 요청 설정
        hashkeyRequest.setCANO("50123231");

        // 실제 서비스 호출
        ResponseEntity<Map<String, Object>> response = hashkeyService.getHashkey(hashkeyRequest, keyRequest);

        // 결과 검증: 응답의 HTTP 상태 코드가 OK(200)이어야 함
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetHashkey_Failure() {
        // 실패 시나리오를 위해 AppSecret을 빈 값으로 설정
        keyRequest.setAppsecret("");  // 빈 값으로 설정하여 실패 시나리오 테스트

        // 실제 서비스 호출
        ResponseEntity<Map<String, Object>> response = hashkeyService.getHashkey(hashkeyRequest, keyRequest);

        // 응답 바디에서 "status"가 "error"인지 검증
        assertEquals("error", Objects.requireNonNull(response.getBody()).get("status"));
    }
}
