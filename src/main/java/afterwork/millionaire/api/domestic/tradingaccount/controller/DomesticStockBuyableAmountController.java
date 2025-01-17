package afterwork.millionaire.api.domestic.tradingaccount.controller;

import afterwork.millionaire.api.domestic.tradingaccount.dto.DomesticStockBuyableAmountRequest;
import afterwork.millionaire.api.domestic.tradingaccount.service.DomesticStockBuyableAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * DomesticStockBuyableAmountController
 * 이 컨트롤러는 국내 주식 매수 가능 금액을 조회하는 API 엔드포인트를 제공합니다.
 * 사용자가 제공한 요청에 따라 매수 가능한 금액을 계산하고 반환하는 역할을 합니다.
 */
@RestController
@RequestMapping("/domestic/domestic-stock/trading/inquire-psamount")
public class DomesticStockBuyableAmountController {

    @Autowired
    private DomesticStockBuyableAmountService domesticStockBuyableAmountService;

    /**
     * 국내 주식 매수 가능 금액을 조회하는 엔드포인트입니다.
     * 사용자가 제공한 계좌 정보 및 주문 정보를 바탕으로 매수 가능한 금액을 계산하여 반환합니다.
     * @param request  사용자가 제공한 요청 데이터를 포함하는 객체
     *                (계좌번호, 계좌상품코드, 종목번호 등 포함)
     * @param headers  요청 헤더 (인증 및 기타 정보 포함)
     * @return 매수 가능 금액 데이터를 포함한 Mono<ResponseEntity>
     */
    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockBuyableAmount(
            @ModelAttribute DomesticStockBuyableAmountRequest request,
            @RequestHeader HttpHeaders headers
    ) {
        // 서비스 계층을 호출하여 매수 가능 금액 데이터를 조회하고 결과를 반환합니다.
        return domesticStockBuyableAmountService.getDomesticStockBuyableAmount(request, headers);
    }
}
