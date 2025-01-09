package afterwork.millionaire.api.oauth.service;

import afterwork.millionaire.api.oauth.dto.TokenRequest;
import afterwork.millionaire.api.oauth.dto.RevokeTokenRequest;
import afterwork.millionaire.config.ApiProperties;
import afterwork.millionaire.util.WebClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * TokenService
 * OAuth2 토큰을 발급받기 위한 서비스입니다.
 * 주어진 요청 정보로 액세스 토큰을 발급받아 반환하며, 토큰 취소 기능도 제공합니다.
 */
@Service
@Slf4j
public class TokenService {

    @Autowired
    private ApiProperties apiProperties;

    /**
     * 액세스 토큰을 비동기적으로 발급받습니다.
     * 주어진 요청 정보와 헤더를 사용하여 액세스 토큰을 발급하고, 결과를 반환합니다.
     *
     * @param request 액세스 토큰 발급을 위한 요청 정보
     * @param headers 요청 헤더에 포함된 인증 정보
     * @return 액세스 토큰 또는 에러 응답
     */
    public Mono<ResponseEntity<Map<String, Object>>> getAccessToken(TokenRequest request, HttpHeaders headers) {
        // WebClient를 사용하여 외부 API로 액세스 토큰 발급 요청을 보내고, 그 결과를 Mono 형태로 반환
        return WebClientUtils.sendPostRequest(apiProperties.getToken(), headers, request);
    }

    /**
     * 액세스 토큰을 비동기적으로 취소합니다.
     * 주어진 요청 정보를 바탕으로 토큰을 취소하고 결과를 반환합니다.
     *
     * @param request 토큰 취소를 위한 요청 정보
     * @param headers 요청 헤더에 포함된 인증 정보
     * @return 토큰 취소 결과 또는 에러 응답
     */
    public Mono<ResponseEntity<Map<String, Object>>> revokeToken(RevokeTokenRequest request, HttpHeaders headers) {
        // WebClient를 사용하여 외부 API로 토큰 취소 요청을 보내고, 그 결과를 Mono 형태로 반환
        return WebClientUtils.sendPostRequest(apiProperties.getRevoke(), headers, request);
    }
}
