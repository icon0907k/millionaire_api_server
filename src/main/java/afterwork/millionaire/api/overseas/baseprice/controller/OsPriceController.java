package afterwork.millionaire.api.overseas.baseprice.controller;

import afterwork.millionaire.api.overseas.baseprice.service.PriceService;
import afterwork.millionaire.api.overseas.baseprice.dto.PriceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * OsPriceController
 * 이 컨트롤러는 해외 주식 가격을 조회하는 API 엔드포인트를 제공합니다.
 * 주식 종목과 거래소 코드, 인증 정보를 기반으로 실시간 주식 가격 데이터를 반환합니다.
 */
@RestController
@RequestMapping("/overseas/overseas-stock/price")
@RequiredArgsConstructor
public class OsPriceController {

    private final OsPriceService osPriceService;

    /**
     * 해외 주식 가격을 조회하는 엔드포인트
     * 이 메서드는 GET 요청을 통해 해외 주식 가격 데이터를 조회하는 기능을 제공합니다.
     * @param request 요청 객체로, 해외 주식 가격을 조회하는 데 필요한 매개변수들을 포함합니다.
     * @param headers 요청 헤더로, 인증 정보와 같은 중요한 메타 데이터를 포함합니다.
     * @return 해외 주식 가격 데이터를 포함한 응답을 반환합니다.
     */
    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockPrice(
            @ModelAttribute OsPriceRequest request,
            @RequestHeader HttpHeaders headers
    ) {
        // 서비스 계층에서 해외 주식 가격을 조회하고, 조회 결과를 반환
        return osPriceService.getOverseasStockPrice(request, headers);
    }
}
