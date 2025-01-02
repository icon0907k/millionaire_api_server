package afterwork.millionaire.api.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RealTimeWebSocketConnectionKeyResponse {
    // 웹소켓 접속키
    private String approval_key;

}