package afterwork.millionaire.api.oauth.controller;

import afterwork.millionaire.api.dto.KeyRequest;
import afterwork.millionaire.api.oauth.service.RealTimeWebSocketConnectionKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * RealTimeConnectionKeyController
 *
 * 실시간 웹소켓 접속키를 발급하는 API 엔드포인트를 제공하는 컨트롤러입니다.
 */
@RestController
@RequestMapping("/oauth/realTimeConnectionKey")
public class RealTimeConnectionKeyController {

    private final RealTimeWebSocketConnectionKeyService connectionKeyService;

    // RealTimeWebSocketConnectionKeyService 의존성 주입
    @Autowired
    public RealTimeConnectionKeyController(RealTimeWebSocketConnectionKeyService connectionKeyService) {
        this.connectionKeyService = connectionKeyService;
    }

    /**
     * 사용자별로 실시간 접속키를 발급하는 엔드포인트입니다.
     *
     * @param userId     사용자 ID
     * @param request    승인 키 요청 정보
     * @param contentType 요청 헤더에서 전달되는 콘텐츠 타입
     * @return 접속키 발급 결과
     */
    @PostMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getConnectionKey(
            @PathVariable String userId,  // URL 경로에서 사용자 ID를 추출
            @RequestBody KeyRequest request,  // 요청 본문에서 승인 키 요청 정보를 받음
            @RequestHeader("Content-Type") String contentType  // 요청 헤더에서 콘텐츠 타입을 받음
    ) {
        // 사용자별로 접속키를 발급하고 결과를 반환
        return connectionKeyService.generateConnectionKey(userId, request, contentType);
    }
}
