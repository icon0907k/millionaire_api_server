package afterwork.millionaire.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ErrorUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ResponseEntity<Map<String, Object>> createErrorResponse(String message, String errorDetails) {
        try {
            // HashMap을 사용하여 수정 가능한 Map을 생성
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", message);

            // errorDetails의 값이 JSON 형식이므로 이를 파싱하여 Map으로 변환
            String detailsJson = extractJsonFromErrorDetails(errorDetails);
            Map<String, String> parsedDetails = objectMapper.readValue(detailsJson, Map.class);

            errorResponse.put("errorDetails", parsedDetails);

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            // JSON 파싱 실패 시 에러 처리
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", message);
            errorResponse.put("errorDetails", e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // errorDetails 안의 JSON 부분을 추출하는 메서드
    private static String extractJsonFromErrorDetails(String errorDetails) {
        // 예시: "errorDetails"의 값이 이 형태일 때, 내부 JSON 부분을 추출
        int start = errorDetails.indexOf("{");
        int end = errorDetails.lastIndexOf("}") + 1;

        return errorDetails.substring(start, end);
    }
}
