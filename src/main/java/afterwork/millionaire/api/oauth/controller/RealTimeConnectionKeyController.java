package afterwork.millionaire.api.oauth.controller;

import afterwork.millionaire.api.oauth.dto.RealTimeConnectionKeyRequest;
import afterwork.millionaire.api.oauth.service.RealTimeWebSocketConnectionKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * RealTimeConnectionKeyController
 * 이 컨트롤러는 실시간 웹소켓 접속키를 발급하는 API 엔드포인트를 제공합니다.
 * 주로 사용자에게 실시간 웹소켓 연결을 위한 접속 키를 발급하는 역할을 합니다.
 */
@RestController
@RequestMapping("/oauth/realTimeConnectionKey")
public class RealTimeConnectionKeyController {

    private final RealTimeWebSocketConnectionKeyService connectionKeyService;

    @Autowired
    public RealTimeConnectionKeyController(RealTimeWebSocketConnectionKeyService connectionKeyService) {
        this.connectionKeyService = connectionKeyService;
    }

    /**
     * 사용자별로 실시간 접속키를 발급하는 엔드포인트입니다.
     * 주어진 사용자 ID와 요청 본문을 바탕으로 실시간 웹소켓 접속키를 발급합니다.
     *
     * @param request    요청 본문에서 실시간 접속키 발급을 위한 승인 키 요청 정보
     * @param headers 요청 헤더에서 전달되는 콘텐츠 타입
     * @return 접속키 발급 결과를 포함한 응답
     */
    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> getConnectionKey(
            @RequestBody RealTimeConnectionKeyRequest request,  // 요청 본문에서 승인 키 요청 정보를 받음
            @RequestHeader HttpHeaders headers  // 요청 헤더에서 콘텐츠 타입을 받음
    ) {
        // 주어진 사용자 ID와 요청 본문을 사용하여 실시간 접속키를 발급하고 결과를 반환
        return connectionKeyService.issueNewConnectionKey(request, headers);
    }
}
