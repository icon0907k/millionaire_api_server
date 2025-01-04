package afterwork.millionaire.api.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * RealTimeConnectionKeyRequest
 *
 * 실시간 접속키 요청에 필요한 파라미터들을 담고 있는 클래스입니다.
 */
@Data
@AllArgsConstructor
public class RealTimeConnectionKeyRequest {

    private String contentType;  // 콘텐츠 타입
    private String grant_type;   // 인증 유형
    private String appkey;       // 앱 키
    private String secretkey;    // 시크릿 키
}
