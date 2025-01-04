package afterwork.millionaire.api.oauth.service;

import afterwork.millionaire.api.oauth.dto.HashkeyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * HashkeyService
 * 외부 API를 통해 해시 키를 요청하고, 응답을 처리하는 서비스 클래스입니다.
 */
@Service
@Slf4j
public class HashkeyService {

    // 해시 키를 요청할 외부 API URL
    private static final String API_URL = "https://openapivts.koreainvestment.com:29443/uapi/hashkey";

    /**
     * 외부 API를 호출하여 해시 키를 요청합니다.
     *
     * @param requestDTO 해시 키 요청에 필요한 정보
     * @param headers   API 호출에 필요한 인증 헤더
     * @return 해시 키 또는 에러 응답
     */
    public ResponseEntity<Map<String, Object>> getHashkey(HashkeyRequest requestDTO, HttpHeaders headers) {
        try {
            // 요청 본문과 헤더를 포함한 HttpEntity 생성
            HttpEntity<HashkeyRequest> entity = new HttpEntity<>(requestDTO, headers);
            RestTemplate restTemplate = new RestTemplate();

            // 외부 API 호출하여 응답 받기

            return restTemplate.exchange(
                    API_URL, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {});
        } catch (Exception e) {
            // 호출 실패 시 에러 로깅 후 에러 응답 반환
            log.error("외부 호출 에러: {}", e.getMessage(), e);
            return afterwork.millionaire.api.util.ErrorUtils.createErrorResponse("외부 호출 에러", e.getMessage());
        }
    }
}
