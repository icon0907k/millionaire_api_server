package afterwork.millionaire.api.oauth.service;

import afterwork.millionaire.api.dto.KeyRequest;
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
 * 실시간 (웹소켓) 접속키 발급받으실 수 있는 API 입니다.
 * 웹소켓 이용 시 해당 키를 appkey와 appsecret 대신 헤더에 넣어 API를 호출합니다.
 * 접속키의 유효기간은 24시간이지만, 접속키는 세션 연결 시 초기 1회만 사용하기 때문에 접속키 인증 후에는
 * 세션종료되지 않는 이상 접속키 신규 발급받지 않으셔도 365일 내내 웹소켓 데이터 수신하실 수 있습니다.
 */
@Service
@Slf4j
public class RealTimeWebSocketConnectionKeyService {

    // 승인 키를 요청할 API URL
    private static final String API_URL = "https://openapi.koreainvestment.com:9443/oauth2/Approval";

    // 승인 키를 저장할 파일 경로
    private static final String CONNECTION_KEY_FILE = "src/main/java/afterwork/millionaire/keys/realTimeWebSocketConnectionKeys.json";

    // JSON 데이터 처리를 위한 ObjectMapper 인스턴스
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 승인 키를 생성하거나 기존 키를 반환합니다.
     *
     * @param userId 사용자 ID
     * @param request 승인 키 요청 정보
     * @param contentType 요청의 콘텐츠 타입
     * @return 승인 키 또는 에러 응답
     */
    public ResponseEntity<Map<String, Object>> generateConnectionKey(String userId, KeyRequest request, String contentType) {
        // 기존 승인 키 로드
        Map<String, Map<String, String>> connectionKeys = loadConnectionKeysFromFile();

        // userId와 appkey를 기준으로 기존 키를 찾음
        Map<String, String> existingKey = findConnectionKey(connectionKeys, userId, request.getAppKey());

        // 키가 존재하는 경우 기존 키 반환
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
     * 주어진 userId와 appkey를 기준으로 승인 키를 찾습니다.
     *
     * @param connectionKeys 기존의 승인 키들
     * @param userId 사용자 ID
     * @param appkey 애플리케이션 키
     * @return 찾은 승인 키 또는 null
     */
    private Map<String, String> findConnectionKey(Map<String, Map<String, String>> connectionKeys, String userId, String appkey) {
        for (Map.Entry<String, Map<String, String>> entry : connectionKeys.entrySet()) {
            Map<String, String> keyMap = entry.getValue();

            // null 체크 추가
            String userIdInMap = keyMap.get("userId");
            String appkeyInMap = keyMap.get("appkey");

            if (userIdInMap != null && appkeyInMap != null && userIdInMap.equals(userId) && appkeyInMap.equals(appkey)) {
                return keyMap;
            }
        }
        return null;
    }

    /**
     * 새로운 승인 키를 API를 통해 발급받습니다.
     *
     * @param userId 사용자 ID
     * @param request 승인 키 요청 정보
     * @param contentType 요청의 콘텐츠 타입
     * @return 새로운 승인 키 또는 에러 응답
     */
    private ResponseEntity<Map<String, Object>> issueNewConnectionKey(String userId, KeyRequest request, String contentType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(contentType));

        // API 요청 생성
        HttpEntity<KeyRequest> entity = new HttpEntity<>(request, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            // API 호출
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    API_URL, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {});

            // API 호출 성공 시 승인 키 저장 및 반환
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                // 응답 본문에서 키 값을 추출
                Map<String, Object> responseBody = response.getBody();

                // 중복 저장 방지
                if (!isKeyAlreadyExists(userId)) {
                    saveConnectionKeyToFile(userId, (String) responseBody.get("approval_key"), request.getAppKey());
                }

                return ResponseEntity.ok(responseBody);
            } else {
                // API 호출 실패 시 에러 처리
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
     * @return 파일에서 로드된 승인 키들
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

        // 중복 저장 방지: 동일한 userId가 있으면 저장하지 않음
        if (connectionKeys.containsKey(userId)) {
            return;
        }

        // 키 추가
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
