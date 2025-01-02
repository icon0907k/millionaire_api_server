package afterwork.millionaire.api.oauth.controller;

import afterwork.millionaire.api.oauth.dto.HashkeyRequest;
import afterwork.millionaire.api.dto.KeyRequest;
import afterwork.millionaire.api.oauth.service.HashkeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * HashkeyController
 *
 * 해시 키를 요청하고 반환하는 API 엔드포인트를 제공하는 컨트롤러입니다.
 */
@Slf4j
@RestController
@RequestMapping("/oauth")
public class HashkeyController {

    // HashkeyService 의존성 주입
    @Autowired
    private HashkeyService hashkeyService;

    /**
     * 해시 키를 발급받는 엔드포인트입니다.
     *
     * @param requestDTO 요청 본문에서 해시 키 요청 정보를 받음
     * @param appkey    요청 헤더에서 앱 키를 받음
     * @param appsecret 요청 헤더에서 앱 시크릿을 받음
     * @param contentType 요청 헤더에서 콘텐츠 타입을 받음
     * @return 해시 키 발급 결과를 포함한 응답
     */
    @PostMapping("/hashkey")
    public ResponseEntity<Map<String, Object>> getHashkey(
            @RequestBody HashkeyRequest requestDTO,  // 요청 본문에서 해시 키 요청 정보를 받음
            @RequestHeader String appkey,           // 요청 헤더에서 앱 키를 받음
            @RequestHeader String appsecret,        // 요청 헤더에서 앱 시크릿을 받음
            @RequestHeader("Content-Type") String contentType  // 요청 헤더에서 콘텐츠 타입을 받음
    ) {
        log.info("getHashkey request");  // 요청이 들어오면 로그 출력

        // 해시 키를 발급받기 위해 HashkeyService의 메서드를 호출하고 반환된 Map을 클라이언트에게 전달
        return hashkeyService.getHashkey(requestDTO, new KeyRequest(appkey, appsecret, contentType));
    }
}
