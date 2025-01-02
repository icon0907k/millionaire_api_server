package afterwork.millionaire.api.oauth.service;

import afterwork.millionaire.api.oauth.dto.RealTimeWebSocketConnectionKeyRequest;
import afterwork.millionaire.api.oauth.dto.RealTimeWebSocketConnectionKeyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ConnectionKeyService
 *
 * 사용자의 승인 키(Connection Key)를 관리하고 API 호출을 통해 키를 발급하는 서비스 클래스입니다.
 */
@Service
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
     * @return 승인 키 또는 에러 응답
     */
    public ResponseEntity<Map<String, String>> generateConnectionKey(String userId, @Valid RealTimeWebSocketConnectionKeyRequest request) {
        // 기존 승인 키 로드
        Map<String, Map<String, String>> connectionKeys = loadConnectionKeysFromFile();

        // 키가 존재하는 경우 기존 키 반환
        if (connectionKeys.containsKey(userId)) {
            Map<String, String> response = new HashMap<>();
            response.put("userId", userId);
            response.put("approval_key", connectionKeys.get(userId).get("approval_key"));
            return ResponseEntity.ok(response);
        }

        // 새로운 승인 키 발급
        return issueNewConnectionKey(userId, request);
    }

    /**
     * 새로운 승인 키를 API를 통해 발급받습니다.
     *
     * @param userId 사용자 ID
     * @param request 승인 키 요청 정보
     * @return 새로운 승인 키 또는 에러 응답
     */
    private ResponseEntity<Map<String, String>> issueNewConnectionKey(String userId, RealTimeWebSocketConnectionKeyRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // API 요청 생성
        HttpEntity<RealTimeWebSocketConnectionKeyRequest> entity = new HttpEntity<>(request, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            // API 호출
            ResponseEntity<RealTimeWebSocketConnectionKeyResponse> response = restTemplate.exchange(
                    API_URL, HttpMethod.POST, entity, RealTimeWebSocketConnectionKeyResponse.class);

            // API 호출 성공 시 승인 키 저장 및 반환
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                String approvalKey = response.getBody().getApproval_key();

                // 중복 저장 방지
                if (!isKeyAlreadyExists(userId)) {
                    saveConnectionKeyToFile(userId, approvalKey);
                }

                Map<String, String> successResponse = new HashMap<>();
                successResponse.put("userId", userId);
                successResponse.put("approval_key", approvalKey);
                return ResponseEntity.ok(successResponse);
            } else {
                // API 호출 실패 시 에러 처리
                return handleApiError(response);
            }

        } catch (Exception ex) {
            // 예외 발생 시 에러 처리
            return handleException(ex);
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
     * API 호출 실패 시 에러 응답 생성
     */
    private ResponseEntity<Map<String, String>> handleApiError(ResponseEntity<RealTimeWebSocketConnectionKeyResponse> response) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "API 호출 오류: " + response.getStatusCode());
        return ResponseEntity.status(response.getStatusCode()).body(errorResponse);
    }

    /**
     * 예외 발생 시 에러 응답 생성
     */
    private ResponseEntity<Map<String, String>> handleException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "서버 오류: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    /**
     * 승인 키 파일 로드
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
     * 승인 키를 파일에 저장
     */
    private void saveConnectionKeyToFile(String userId, String approvalKey) {
        Map<String, Map<String, String>> connectionKeys = loadConnectionKeysFromFile();

        // 중복 저장 방지: 동일한 userId가 있으면 저장하지 않음
        if (connectionKeys.containsKey(userId)) {
            return;
        }

        // 키 추가
        Map<String, String> keyMap = new HashMap<>();

        keyMap.put("approval_key", approvalKey);
        keyMap.put("userId", userId);

        connectionKeys.put(userId, keyMap);

        try {
            objectMapper.writeValue(new File(CONNECTION_KEY_FILE), connectionKeys);
        } catch (IOException e) {
            throw new RuntimeException("접속키 저장 실패", e);
        }
    }
}
