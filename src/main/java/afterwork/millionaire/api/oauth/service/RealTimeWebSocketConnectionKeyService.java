package afterwork.millionaire.api.oauth.service;

import afterwork.millionaire.api.oauth.dto.RealTimeConnectionKeyRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 실시간 웹소켓 접속키를 관리하는 서비스.
 * 1회 접속키 발급 후 365일 동안 유효한 세션을 유지합니다.
 */
@Service
@Slf4j
public class RealTimeWebSocketConnectionKeyService {

    private static final String API_URL = "https://openapivts.koreainvestment.com:29443/oauth2/Approval";
    private static final String CONNECTION_KEY_FILE = "src/main/java/afterwork/millionaire/keys/realTimeWebSocketConnectionKeys.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 승인 키를 생성하거나 기존 키를 반환합니다.
     *
     * @param userId      사용자 ID
     * @param request     승인 키 요청 정보
     * @param contentType 요청의 콘텐츠 타입
     * @return 승인 키 또는 에러 응답
     */
    public ResponseEntity<Map<String, Object>> generateConnectionKey(String userId, RealTimeConnectionKeyRequest request, String contentType) {
        Map<String, Map<String, String>> connectionKeys = loadConnectionKeysFromFile();

        // 기존 승인 키 조회
        Map<String, String> existingKey = findConnectionKey(connectionKeys, userId, request.getAppkey());
        if (existingKey != null) {
            return ResponseEntity.ok(createResponse(userId, existingKey.get("approval_key")));
        }

        // 새로운 승인 키 발급
        return issueNewConnectionKey(userId, request, contentType);
    }

    /**
     * 기존 승인 키를 찾습니다.
     *
     * @param connectionKeys 기존 승인 키들
     * @param userId         사용자 ID
     * @param appkey         애플리케이션 키
     * @return 기존 승인 키 또는 null
     */
    private Map<String, String> findConnectionKey(Map<String, Map<String, String>> connectionKeys, String userId, String appkey) {
        return connectionKeys.values().stream()
                .filter(key -> userId.equals(key.get("userId")) && appkey.equals(key.get("appkey")))
                .findFirst()
                .orElse(null);
    }

    /**
     * 새로운 승인 키를 발급받습니다.
     *
     * @param userId      사용자 ID
     * @param request     승인 키 요청 정보
     * @param contentType 요청 콘텐츠 타입
     * @return 승인 키 또는 에러 응답
     */
    private ResponseEntity<Map<String, Object>> issueNewConnectionKey(String userId, RealTimeConnectionKeyRequest request, String contentType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(contentType));
        HttpEntity<RealTimeConnectionKeyRequest> entity = new HttpEntity<>(request, headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    API_URL, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {});

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                saveConnectionKey(userId, (String) responseBody.get("approval_key"), request.getAppkey());
                return ResponseEntity.ok(responseBody);
            }

            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            log.error("외부 호출 에러: {}", e.getMessage(), e);
            return afterwork.millionaire.api.util.ErrorUtils.createErrorResponse("외부 호출 에러", e.getMessage());
        }
    }

    /**
     * 승인 키 파일에서 데이터를 로드합니다.
     *
     * @return 승인 키 데이터
     */
    private Map<String, Map<String, String>> loadConnectionKeysFromFile() {
        try {
            File file = new File(CONNECTION_KEY_FILE);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<>() {});
            }
        } catch (IOException e) {
            log.error("파일 읽기 실패: {}", CONNECTION_KEY_FILE, e);
        }
        return new HashMap<>();
    }

    /**
     * 승인 키를 파일에 저장합니다.
     *
     * @param userId      사용자 ID
     * @param approvalKey 승인 키
     * @param appkey      애플리케이션 키
     */
    private void saveConnectionKey(String userId, String approvalKey, String appkey) {
        Map<String, Map<String, String>> connectionKeys = loadConnectionKeysFromFile();
        if (!connectionKeys.containsKey(userId)) {
            Map<String, String> keyMap = new HashMap<>();
            keyMap.put("approval_key", approvalKey);
            keyMap.put("userId", userId);
            keyMap.put("appkey", appkey);
            connectionKeys.put(userId, keyMap);

            try {
                objectMapper.writeValue(new File(CONNECTION_KEY_FILE), connectionKeys);
            } catch (IOException e) {
                log.error("접속 키 저장 실패: {}", CONNECTION_KEY_FILE, e);
            }
        }
    }

    /**
     * 승인 키 응답 데이터를 생성합니다.
     *
     * @param userId      사용자 ID
     * @param approvalKey 승인 키
     * @return 응답 데이터
     */
    private Map<String, Object> createResponse(String userId, String approvalKey) {
        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("approval_key", approvalKey);
        return response;
    }
}
