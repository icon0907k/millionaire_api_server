package afterwork.millionaire.api.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * RevokeTokenRequest
 * 이 클래스는 액세스 토큰을 취소하는 요청 정보를 나타냅니다.
 * 토큰을 취소하기 위한 앱 키, 앱 시크릿, 그리고 취소할 토큰 정보를 포함합니다.
 */
@Data
@AllArgsConstructor
public class RevokeTokenRequest {
    private String appkey;    // 앱 키
    private String appsecret; // 앱 시크릿
    private String token;     // 취소할 토큰
}
