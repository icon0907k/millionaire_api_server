package afterwork.millionaire.api.oauth.service;

import afterwork.millionaire.api.dto.KeyRequest;
import afterwork.millionaire.api.oauth.dto.HashkeyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * HashkeyService
 *
 * 해쉬키(Hashkey)는 보안을 위한 요소로 사용자가 보낸 요청 값을 중간에 탈취하여 변조하지 못하도록 하는데 사용됩니다.
 * 해쉬키를 사용하면 POST로 보내는 요청(주로 주문/정정/취소 API 해당)의 body 값을 사전에 암호화시킬 수 있습니다.
 * 해쉬키는 비필수값으로 사용하지 않아도 POST API 호출은 가능합니다.
 */
@Service
@Slf4j
public class HashkeyService {

    // 해시 키를 요청할 API URL
    private static final String API_URL = "https://openapi.koreainvestment.com:9443/uapi/hashkey";

    /**
     * 외부 API를 통해 해시 키를 요청합니다.
     *
     * @param requestDTO 해시 키 요청 정보
     * @param keyRequest API 호출에 필요한 인증 정보
     * @return 해시 키 또는 에러 응답
     */
    public ResponseEntity<Map<String, Object>> getHashkey(HashkeyRequest requestDTO, KeyRequest keyRequest) {
        try {
            // 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.set("content-type", keyRequest.getContentType());  // 콘텐츠 타입 설정
            headers.set("appkey", keyRequest.getAppKey());              // 앱 키 설정
            headers.set("appsecret", keyRequest.getAppsecret());        // 앱 시크릿 설정

            // 요청 본문 및 헤더를 포함한 HttpEntity 생성
            HttpEntity<HashkeyRequest> entity = new HttpEntity<>(requestDTO, headers);
            RestTemplate restTemplate = new RestTemplate();

            // 외부 API 호출, ParameterizedTypeReference로 응답 타입 지정
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    API_URL, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {});

            return response;
        } catch (Exception e) {
            // 외부 호출 실패 시 에러 로깅 및 에러 응답 반환
            log.error("외부 호출 에러: {}", e.getMessage(), e);
            return afterwork.millionaire.api.util.ErrorUtils.createErrorResponse("외부 호출 에러", e.getMessage());
        }
    }
}
