package afterwork.millionaire.api.oauth.service;

import afterwork.millionaire.api.oauth.dto.HashkeyRequest;
import afterwork.millionaire.config.ApiProperties;
import afterwork.millionaire.util.WebClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * HashkeyService
 * 외부 API를 통해 해시 키를 요청하고, 응답을 처리하는 서비스 클래스입니다.
 * 이 서비스는 해시 키를 외부 API에서 요청하고 결과를 반환하는 역할을 합니다.
 */
@Service
@RequiredArgsConstructor
public class HashkeyService {

    private final ApiProperties apiProperties;

    /**
     * 외부 API를 호출하여 해시 키를 요청합니다.
     * WebClient를 사용하여 요청을 보내고, 그 응답을 반환합니다.
     * @param request 해시 키 요청에 필요한 정보 객체
     * @param headers API 호출에 필요한 인증 헤더 정보
     * @return 해시 키 발급 결과를 포함한 응답 또는 에러 응답
     */
    public Mono<ResponseEntity<Map<String, Object>>> getHashkey(HashkeyRequest request, HttpHeaders headers) {
        // WebClient를 사용하여 외부 API로 해시 키 요청을 보내고, 결과를 Mono 형태로 반환
        return WebClientUtils.sendPostRequest(apiProperties.getHashkey(), headers, request);
    }

}
