package afterwork.millionaire.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * KeyRequest
 *
 * 공통 요청 값 정의 클래스입니다.
 * 이 클래스는 애플리케이션의 다양한 요청에서 사용되는 공통 값을 담고 있습니다.
 */
@Data
@AllArgsConstructor
public class KeyRequest {

    // 요청 콘텐츠 타입
    private String contentType;
    // 인증 그랜트 타입
    private String grantType;
    // 애플리케이션 키
    private String appKey;
    // 비밀 키
    private String secretKey;
    // 애플리케이션 시크릿
    private String appsecret;

    /**
     * 특정 값만 받는 생성자
     * @param appKey 애플리케이션 키
     * @param appsecret 애플리케이션 시크릿
     * @param contentType 요청 콘텐츠 타입
     */
    public KeyRequest(String appKey, String appsecret, String contentType) {
        this.appKey = appKey;
        this.appsecret = appsecret;
        this.contentType = contentType;
    }

    public KeyRequest() {
    }
}
