package afterwork.millionaire.api.oauth.controller;

import afterwork.millionaire.api.oauth.dto.HashkeyRequest;
import afterwork.millionaire.api.oauth.service.HashkeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * HashkeyController
 * 이 컨트롤러는 해시 키를 요청하고 반환하는 API 엔드포인트를 제공합니다.
 * 주로 외부 시스템과의 보안 통신을 위해 해시 키를 발급받는 역할을 합니다.
 */
@Slf4j
@RestController
@RequestMapping("/oauth/hashkey")
public class HashkeyController {

    // HashkeyService 의존성 주입
    @Autowired
    private HashkeyService hashkeyService;

    /**
     * 해시 키를 발급받는 엔드포인트입니다.
     * 요청 본문에 포함된 해시 키 요청 정보를 사용하여 해시 키를 생성합니다.
     *
     * @param requestDTO 해시 키를 요청하는 본문 데이터 객체
     * @param appkey 요청 헤더에서 전달된 앱 키
     * @param appsecret 요청 헤더에서 전달된 앱 시크릿
     * @param contentType 요청 헤더에서 전달된 콘텐츠 타입
     * @return 해시 키 발급 결과를 포함한 응답을 반환
     */
    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> getHashkey(
            @RequestBody HashkeyRequest requestDTO,  // 요청 본문에서 해시 키 요청 정보를 받음
            @RequestHeader String appkey,           // 요청 헤더에서 앱 키를 받음
            @RequestHeader String appsecret,        // 요청 헤더에서 앱 시크릿을 받음
            @RequestHeader("Content-Type") String contentType  // 요청 헤더에서 콘텐츠 타입을 받음
    ) {
        // 요청 헤더 설정: 콘텐츠 타입, 앱 키, 앱 시크릿을 헤더에 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", contentType);
        headers.set("appkey", appkey);
        headers.set("appsecret", appsecret);

        // HashkeyService의 메서드를 호출하여 해시 키를 생성하고, 생성된 해시 키를 클라이언트에게 반환
        return hashkeyService.getHashkey(requestDTO, headers);
    }
}
