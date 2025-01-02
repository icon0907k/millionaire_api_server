package afterwork.millionaire.api.oauth.controller;

import afterwork.millionaire.api.oauth.dto.RealTimeWebSocketConnectionKeyRequest;
import afterwork.millionaire.api.oauth.service.RealTimeWebSocketConnectionKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
@RestController
@Validated
public class RealTimeConnectionKeyController {

    private final RealTimeWebSocketConnectionKeyService connectionKeyService;

    @Autowired
    public RealTimeConnectionKeyController(RealTimeWebSocketConnectionKeyService connectionKeyService) {
        this.connectionKeyService = connectionKeyService;
    }

    // 실시간 (웹소켓) 접속키 발급
    @PostMapping("/api/connection-key/{userId}")
    public ResponseEntity<Map<String, String>> getConnectionKey(
            @PathVariable String userId,
            @Valid @RequestBody RealTimeWebSocketConnectionKeyRequest request) {
        // 사용자별로 접속키 발급
        return connectionKeyService.generateConnectionKey(userId, request);
    }
}
