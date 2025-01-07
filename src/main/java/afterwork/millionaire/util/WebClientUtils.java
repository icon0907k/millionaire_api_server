package afterwork.millionaire.util;

import afterwork.millionaire.config.ApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * WebClientUtils는 WebClient를 사용하여 외부 API 호출을 단순화하는 유틸리티 클래스입니다.
 * 이 클래스는 WebClient 인스턴스를 관리하며, 외부 API 호출을 위한 공통 메서드를 제공합니다.
 */
@Component
@Slf4j
public class WebClientUtils {

    private static WebClient webClient; // WebClient 인스턴스를 static 필드로 선언하여 재사용

    /**
     * WebClientUtils 생성자.
     * Spring의 @Autowired를 사용하여 WebClient.Builder와 ApiProperties를 주입받습니다.
     * 주입받은 ApiProperties를 기반으로 WebClient 인스턴스를 초기화합니다.
     *
     * @param webClientBuilder WebClient를 생성하는 빌더 객체
     * @param apiProperties API 관련 설정 정보를 포함한 객체
     */
    @Autowired
    public WebClientUtils(WebClient.Builder webClientBuilder, ApiProperties apiProperties) {
        // WebClient 초기화
        webClient = webClientBuilder.baseUrl(apiProperties.getBaseUrl()).build();
    }

    /**
     * 외부 API에 POST 요청을 보내는 메서드.
     * 요청 URL, 헤더, 본문 데이터를 받아 비동기적으로 처리합니다.
     *
     * @param url 요청을 보낼 URL (Base URL 이후 경로)
     * @param headers 요청에 포함할 HTTP 헤더
     * @param body 요청에 포함할 본문 데이터
     * @param <T> 요청 본문의 데이터 타입
     * @return Mono<ResponseEntity<Map<String, Object>>> 응답 데이터를 포함한 Mono 객체
     */
    public static <T> Mono<ResponseEntity<Map<String, Object>>> sendPostRequest(
            String url,
            HttpHeaders headers,
            T body
    ) {
        // WebClient를 사용하여 POST 요청 생성 및 실행
        return webClient.post()
                .uri(url) // URL 설정
                .headers(httpHeaders -> httpHeaders.addAll(headers)) // 요청 헤더 설정
                .bodyValue(body) // 요청 본문 설정
                .retrieve() // 요청 실행 및 응답 처리
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {}) // 응답 데이터를 Map 형태로 변환
                .flatMap(responseBody ->
                        // 응답 데이터를 ResponseEntity로 감싸서 반환
                        Mono.just(ResponseEntity.ok(responseBody))
                )
                .onErrorResume(e -> {
                    // 요청 중 예외 발생 시 처리
                    log.error("외부 호출 에러: {}", e.getMessage(), e); // 에러 로그 출력
                    // 에러 응답을 생성하여 반환
                    return Mono.just(ErrorUtils.createErrorResponse("외부 호출 에러", e));
                });
    }

    /**
     * 외부 API에 GET 요청을 보내는 공통 메서드.
     * 요청 URL, 헤더, 쿼리 파라미터 데이터를 받아 비동기적으로 처리합니다.
     *
     * @param url 요청을 보낼 URL (Base URL 이후 경로)
     * @param headers 요청에 포함할 HTTP 헤더
     * @param queryParams 요청에 포함할 쿼리 파라미터
     * @return Mono<ResponseEntity<Map<String, Object>>> 응답 데이터를 포함한 Mono 객체
     */
    public static <T> Mono<ResponseEntity<Map<String, Object>>> sendGetRequest(
            String url,
            HttpHeaders headers,
            T queryParams
    ) {
        // WebClient를 사용하여 GET 요청 생성 및 실행
        return webClient.get()
                .uri(uriBuilder -> {
                    var uriBuilderWithParams = uriBuilder.path(url);
                    // queryParams가 null이 아니면 리플렉션을 사용해 필드들을 순회하여 queryParam 추가
                    Field[] fields = queryParams.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true); // private 필드 접근 허용
                        Object value = null;
                        try {
                            value = field.get(queryParams);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                        if (value != null) {
                            // 필드 이름과 값을 queryParam으로 추가
                            uriBuilderWithParams.queryParam(field.getName(), value.toString());
                        }
                    }
                    return uriBuilderWithParams.build();
                })
                .headers(httpHeaders -> httpHeaders.addAll(headers)) // 요청 헤더 설정
                .retrieve() // 요청 실행 및 응답 처리
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {}) // 응답 데이터를 Map 형태로 변환
                .flatMap(responseBody ->
                        // 응답 데이터를 ResponseEntity로 감싸서 반환
                        Mono.just(ResponseEntity.ok(responseBody))
                )
                .onErrorResume(e -> {
                    // 예외 발생 시 처리
                    log.error("외부 호출 에러: {}", e.getMessage(), e);
                    return Mono.just(ErrorUtils.createErrorResponse("외부 호출 에러", e));
                });
    }
}
