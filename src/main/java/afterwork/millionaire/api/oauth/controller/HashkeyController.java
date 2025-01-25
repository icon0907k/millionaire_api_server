package afterwork.millionaire.api.oauth.controller;

import afterwork.millionaire.api.oauth.dto.HashkeyRequest;
import afterwork.millionaire.api.oauth.service.HashkeyService;
import lombok.RequiredArgsConstructor;
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
 * 클라이언트가 해시 키를 요청하면, 요청 본문에 포함된 정보를 바탕으로 해시 키를 생성하여 응답합니다.
 */
@Slf4j
@RestController
@RequestMapping("/oauth/hashkey")
@RequiredArgsConstructor
public class HashkeyController {

    private final HashkeyService hashkeyService;

    /**
     * 해시 키를 발급받는 엔드포인트입니다.
     * 클라이언트는 해시 키 요청 정보를 본문에 포함하여 요청합니다.
     * 요청에 포함된 정보에 기반하여 해시 키를 생성하고, 결과를 반환합니다.
     * @param request 해시 키 요청 정보를 포함한 객체
     * @param headers 요청 헤더에 포함된 앱 키, 시크릿 및 콘텐츠 타입 정보
     * @return 생성된 해시 키와 관련된 응답을 반환
     */
    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> getHashkey(
            @RequestBody HashkeyRequest request,
            @RequestHeader HttpHeaders headers
    ) {
        // HashkeyService의 메서드를 호출하여 해시 키를 생성하고, 생성된 해시 키를 클라이언트에게 반환
        return hashkeyService.getHashkey(request, headers);
    }
}
