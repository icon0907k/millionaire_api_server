package afterwork.millionaire.api.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RealTimeWebSocketConnectionKeyRequest {
    private String grant_type;
    private String appkey;
    private String secretkey;
}
