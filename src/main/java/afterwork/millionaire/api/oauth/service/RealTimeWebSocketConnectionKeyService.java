package afterwork.millionaire.api.oauth.service;

import afterwork.millionaire.api.oauth.dto.RealTimeConnectionKeyRequest;
import afterwork.millionaire.config.ApiProperties;
import afterwork.millionaire.util.WebClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 실시간 웹소켓 접속키를 관리하는 서비스.
 * 1회 접속키 발급 후 365일 동안 유효한 세션을 유지합니다.
 * 이 서비스는 실시간 웹소켓 접속을 위한 승인 키를 발급하는 역할을 합니다.
 */
@Service
public class RealTimeWebSocketConnectionKeyService {

    @Autowired
    private ApiProperties apiProperties;

    /**
     * 새로운 승인 키를 발급받습니다.
     * 주어진 요청 정보와 헤더를 바탕으로 승인 키를 발급하고, 그 결과를 반환합니다.
     * @param request 승인 키 발급 요청 정보
     * @param headers 요청 헤더에 포함된 콘텐츠 타입
     * @return 승인 키 발급 결과 또는 에러 응답
     */
    public Mono<ResponseEntity<Map<String, Object>>> issueNewConnectionKey(RealTimeConnectionKeyRequest request, HttpHeaders headers) {
        // WebClient를 사용하여 외부 API로 승인 키 발급 요청을 보내고, 결과를 Mono 형태로 반환
        return WebClientUtils.sendPostRequest(apiProperties.getApproval(), headers, request);
    }

}
