package afterwork.millionaire.api.domestic.tradingaccount.controller;

import afterwork.millionaire.api.domestic.tradingaccount.dto.DomesticStockTradingOrderRequest;
import afterwork.millionaire.api.domestic.tradingaccount.service.DomesticStockTradingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * DomesticStockTradingOrderController
 * 국내 주식 거래 주문을 처리하는 API를 제공하는 컨트롤러 클래스입니다.
 */
@RestController
@RequestMapping("/domestic/domestic-stock/trading/order")
public class DomesticStockTradingOrderController {

    @Autowired
    private DomesticStockTradingOrderService domesticStockTradingOrderService;

    /**
     * 국내 주식 거래 주문을 처리하는 엔드포인트입니다.
     * @param request 거래 주문 정보를 포함한 요청 본문
     * @param headers 요청 헤더 (앱 키, 시크릿, 콘텐츠 타입 등)
     * @return 거래 주문 결과를 포함한 응답
     */
    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> domesticStockTradingOrder(
            @RequestBody DomesticStockTradingOrderRequest request,
            @RequestHeader HttpHeaders headers
    ) {
        // 서비스 호출을 통해 거래 주문 처리
        return domesticStockTradingOrderService.domesticStockTradingOrder(request, headers);
    }
}
