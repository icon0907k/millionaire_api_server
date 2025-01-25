package afterwork.millionaire.api.overseas.tradingaccount.controller;

import afterwork.millionaire.api.overseas.tradingaccount.dto.OsBuyableAmountRequest;
import afterwork.millionaire.api.overseas.tradingaccount.service.OsBuyableAmountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * OsBuyableAmountController
 * 이 컨트롤러는 해외 주식 거래 가능한 금액을 조회하는 API 엔드포인트를 제공합니다.
 * 사용자가 제공한 인증 정보와 요청에 따라 해외 주식 거래 가능한 금액을 반환합니다.
 */
@RestController
@RequestMapping("/overseas/overseas-stock/trading/inquire-psamount")
@RequiredArgsConstructor
public class OsBuyableAmountController {

    private final OsBuyableAmountService osBuyableAmountService;

    /**
     * 해외 주식 거래 가능한 금액을 조회하는 엔드포인트
     * 이 메서드는 GET 요청을 통해 해외 주식 거래 가능한 금액을 조회하는 기능을 제공합니다.
     * @param request 요청 객체로, 해외 주식 거래 가능한 금액 조회에 필요한 매개변수를 포함합니다.
     * @param headers 요청 헤더로, 인증 정보와 같은 중요한 메타 데이터를 포함합니다.
     * @return 해외 주식 거래 가능한 금액 데이터를 포함한 응답을 반환합니다.
     */
    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockBuyableAmount(
            @ModelAttribute OsBuyableAmountRequest request,
            @RequestHeader HttpHeaders headers
    ){
        // 서비스 계층에서 해외 주식 거래 가능한 금액을 조회하고, 조회 결과를 반환
        return osBuyableAmountService.getOverseasStockBuyableAmount(request, headers);
    }
}
