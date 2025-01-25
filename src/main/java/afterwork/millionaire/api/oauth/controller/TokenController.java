package afterwork.millionaire.api.oauth.controller;

import afterwork.millionaire.api.oauth.dto.RevokeTokenRequest;
import afterwork.millionaire.api.oauth.dto.TokenRequest;
import afterwork.millionaire.api.oauth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * TokenController
 * 이 컨트롤러는 OAuth 토큰을 발급하는 API 엔드포인트를 제공합니다.
 * 주로 사용자가 인증을 위해 필요한 액세스 토큰을 발급받을 수 있는 기능을 제공합니다.
 */
@RestController
@RequestMapping("/oauth/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    /**
     * 액세스 토큰을 발급받는 엔드포인트입니다.
     * 요청 본문에 포함된 토큰 요청 정보를 사용하여 액세스 토큰을 발급합니다.
     * @param request 토큰 발급을 위한 요청 본문 데이터 객체
     * @return 액세스 토큰 발급 결과를 포함한 응답
     */
    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> generateToken(@RequestBody TokenRequest request, @RequestHeader HttpHeaders headers) {
        // TokenService의 메서드를 호출하여 액세스 토큰을 발급하고 결과를 반환
        return tokenService.getAccessToken(request, headers);
    }

    /**
     * 액세스 토큰을 취소하는 엔드포인트입니다.
     * 요청 본문에 포함된 취소 토큰 정보를 사용하여 액세스 토큰을 취소합니다.
     * @param request 취소할 토큰의 요청 본문 데이터 객체
     * @return 토큰 취소 결과를 포함한 응답
     */
    @PostMapping("/revoke")
    public Mono<ResponseEntity<Map<String, Object>>> revokeToken(@RequestBody RevokeTokenRequest request, @RequestHeader HttpHeaders headers) {
        // TokenService의 메서드를 호출하여 액세스 토큰을 취소하고 결과를 반환
        return tokenService.revokeToken(request, headers);
    }
}
