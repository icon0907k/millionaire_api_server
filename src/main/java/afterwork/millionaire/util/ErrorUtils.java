package afterwork.millionaire.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ErrorUtils {

    // ObjectMapper 인스턴스, JSON을 처리하기 위해 사용
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 에러 메시지와 세부 정보를 포함하는 응답을 생성하는 메서드.
     * 이 메서드는 WebClientResponseException을 받아서 이를 처리하고,
     * 에러 메시지와 추가적인 세부 정보를 JSON 형태로 반환합니다.
     *
     * @param message 에러 메시지
     * @param errorDetails 예외 세부 정보 (WebClientResponseException)
     * @return 에러 응답을 포함하는 ResponseEntity 객체
     */
    public static ResponseEntity<Map<String, Object>> createErrorResponse(String message, Throwable errorDetails) {
        try {
            // HashMap을 사용하여 수정 가능한 Map을 생성
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");  // 상태는 항상 'error'
            errorResponse.put("message", message);  // 사용자 지정 메시지 추가

            // WebClientResponseException 타입의 예외를 캐스팅
            WebClientResponseException webClientError = (WebClientResponseException) errorDetails;
            String errorMessage = webClientError.getResponseBodyAsString();  // 응답 본문을 문자열로 가져옴

            // errorDetails의 값이 JSON 형식이므로 이를 파싱하여 Map으로 변환
            String detailsJson = extractJsonFromErrorDetails(errorMessage);  // JSON 부분 추출
            Map<String, String> parsedDetails = objectMapper.readValue(detailsJson, Map.class);  // JSON 파싱

            errorResponse.put("errorDetails", parsedDetails);  // 파싱된 세부 정보 추가

            // 응답 생성: HTTP 상태 코드 500 (서버 내부 오류)
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            // JSON 파싱 실패 시 에러 처리
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");  // 상태는 항상 'error'
            errorResponse.put("message", message);  // 사용자 지정 메시지 추가
            errorResponse.put("errorDetails", e.getMessage());  // 예외 메시지 추가

            // 응답 생성: HTTP 상태 코드 500 (서버 내부 오류)
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * errorDetails 안에서 JSON 부분을 추출하는 메서드.
     * errorDetails가 문자열로 되어있을 때, JSON 형식의 내용을 추출하여 반환합니다.
     *
     * @param errorDetails 에러 메시지 내의 JSON 부분을 포함한 문자열
     * @return JSON 부분만 추출한 문자열
     */
    private static String extractJsonFromErrorDetails(String errorDetails) {
        // 예시: "errorDetails"의 값이 이 형태일 때, 내부 JSON 부분을 추출
        int start = errorDetails.indexOf("{");  // JSON 시작 위치
        int end = errorDetails.lastIndexOf("}") + 1;  // JSON 끝 위치

        return errorDetails.substring(start, end);  // JSON 부분 추출하여 반환
    }
}
