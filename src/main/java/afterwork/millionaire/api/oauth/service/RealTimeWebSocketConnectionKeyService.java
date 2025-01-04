package afterwork.millionaire.api.oauth.service;

import afterwork.millionaire.api.oauth.dto.RealTimeConnectionKeyRequest;
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
 * RealTimeWebSocketConnectionKeyService
 *
 * 실시간 웹소켓 접속키를 발급하고 관리하는 서비스입니다.
 * 1회 접속키 발급 후 365일 동안 유효한 세션 유지.
 */
@Service
@Slf4j
public class RealTimeWebSocketConnectionKeyService {

    // 승인 키를 요청할 API URL
    private static final String API_URL = "https://openapivts.koreainvestment.com:29443/oauth2/Approval";

    // 승인 키 저장 파일 경로
    private static final String CONNECTION_KEY_FILE = "src/main/java/afterwork/millionaire/keys/realTimeWebSocketConnectionKeys.json";

    // JSON 데이터 처리를 위한 ObjectMapper
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 승인 키를 생성하거나 기존 키를 반환합니다.
     *
     * @param userId 사용자 ID
     * @param request 승인 키 요청 정보
     * @param contentType 요청의 콘텐츠 타입
     * @return 승인 키 또는 에러 응답
     */
    public ResponseEntity<Map<String, Object>> generateConnectionKey(String userId, RealTimeConnectionKeyRequest request, String contentType) {
        // 기존 승인 키 로드
        Map<String, Map<String, String>> connectionKeys = loadConnectionKeysFromFile();

        // 기존 키 찾기
        Map<String, String> existingKey = findConnectionKey(connectionKeys, userId, request.getAppkey());

        // 기존 키가 있으면 반환
        if (existingKey != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("userId", userId);
            response.put("approval_key", existingKey.get("approval_key"));
            return ResponseEntity.ok(response);
        }

        // 새로운 승인 키 발급
        return issueNewConnectionKey(userId, request, contentType);
    }

    /**
     * 주어진 userId와 appkey로 기존 승인 키를 찾습니다.
     *
     * @param connectionKeys 기존 승인 키들
     * @param userId 사용자 ID
     * @param appkey 애플리케이션 키
     * @return 기존 승인 키 또는 null
     */
    private Map<String, String> findConnectionKey(Map<String, Map<String, String>> connectionKeys, String userId, String appkey) {
        for (Map.Entry<String, Map<String, String>> entry : connectionKeys.entrySet()) {
            Map<String, String> keyMap = entry.getValue();

            // userId와 appkey가 일치하는 키 반환
            if (keyMap.get("userId").equals(userId) && keyMap.get("appkey").equals(appkey)) {
                return keyMap;
            }
        }
        return null;
    }

    /**
     * 새로운 승인 키를 API로 발급받습니다.
     *
     * @param userId 사용자 ID
     * @param request 승인 키 요청 정보
     * @param contentType 요청 콘텐츠 타입
     * @return 승인 키 또는 에러 응답
     */
    private ResponseEntity<Map<String, Object>> issueNewConnectionKey(String userId, RealTimeConnectionKeyRequest request, String contentType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(contentType));

        // API 요청
        HttpEntity<RealTimeConnectionKeyRequest> entity = new HttpEntity<>(request, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            // 외부 API 호출
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    API_URL, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {});

            // 성공 시 승인 키 저장 및 반환
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();

                // 중복 저장 방지
                if (!isKeyAlreadyExists(userId)) {
                    saveConnectionKeyToFile(userId, (String) responseBody.get("approval_key"), request.getAppkey());
                }

                return ResponseEntity.ok(responseBody);
            } else {
                return ResponseEntity.ok(response.getBody());
            }

        } catch (Exception e) {
            log.error("외부 호출 에러: {}", e.getMessage(), e);
            return afterwork.millionaire.api.util.ErrorUtils.createErrorResponse("외부 호출 에러", e.getMessage());
        }
    }

    /**
     * 이미 저장된 승인 키가 있는지 확인합니다.
     *
     * @param userId 사용자 ID
     * @return 중복 여부
     */
    private boolean isKeyAlreadyExists(String userId) {
        Map<String, Map<String, String>> connectionKeys = loadConnectionKeysFromFile();
        return connectionKeys.containsKey(userId);
    }

    /**
     * 승인 키 파일을 로드합니다.
     *
     * @return 저장된 승인 키들
     */
    private Map<String, Map<String, String>> loadConnectionKeysFromFile() {
        try {
            File file = new File(CONNECTION_KEY_FILE);
            if (file.exists()) {
                return objectMapper.readValue(file, HashMap.class);
            } else {
                return new HashMap<>();
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 읽기 실패: " + CONNECTION_KEY_FILE, e);
        }
    }

    /**
     * 승인 키를 파일에 저장합니다.
     *
     * @param userId 사용자 ID
     * @param approvalKey 승인 키
     * @param appkey 애플리케이션 키
     */
    private void saveConnectionKeyToFile(String userId, String approvalKey, String appkey) {
        Map<String, Map<String, String>> connectionKeys = loadConnectionKeysFromFile();

        // 중복 저장 방지
        if (connectionKeys.containsKey(userId)) {
            return;
        }

        // 승인 키 저장
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("approval_key", approvalKey);
        keyMap.put("userId", userId);
        keyMap.put("appkey", appkey);

        connectionKeys.put(userId, keyMap);

        try {
            objectMapper.writeValue(new File(CONNECTION_KEY_FILE), connectionKeys);
        } catch (IOException e) {
            throw new RuntimeException("접속키 저장 실패", e);
        }
    }
}
