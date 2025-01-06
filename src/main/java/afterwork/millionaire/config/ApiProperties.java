package afterwork.millionaire.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ApiProperties
 * 애플리케이션 설정 파일(application.properties 또는 application.yml)에서
 * API 관련 속성들을 로드하는 클래스입니다.
 * `@ConfigurationProperties`를 사용하여 API 관련 속성들을 주입받습니다.
 */
@Configuration
@ConfigurationProperties(prefix = "api")  // 'api' 접두어로 시작하는 속성들을 로드
@Getter
public class ApiProperties {

    // API 기본 URL
    private String baseUrl;

    // 토큰 발급 URI
    private String token;

    // 토큰 취소 URI
    private String revoke;

    // 해시 키 요청 URI
    private String hashkey;

    // 승인 관련 URI
    private String Approval;
}
