package afterwork.millionaire.api.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RevokeTokenRequest {
    private String appkey;
    private String appsecret;
    private String token;
}