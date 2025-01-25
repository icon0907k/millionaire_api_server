package afterwork.millionaire.api.overseas.tradingaccount.controller;

import afterwork.millionaire.api.overseas.tradingaccount.dto.OsTradingOrderRequest;
import afterwork.millionaire.api.overseas.tradingaccount.service.OsTradingOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * OsTradingOrderController
 * 이 컨트롤러는 해외 주식 거래 주문을 처리하는 API 엔드포인트를 제공합니다.
 * 사용자가 제공한 요청에 따라 해외 주식의 거래 주문을 처리하고 결과를 반환합니다.
 */
@RestController
@RequestMapping("/overseas/overseas-stock/trading/order")
@RequiredArgsConstructor
public class OsTradingOrderController {

    private final OsTradingOrderService osTradingOrderService;

    /**
     * 해외 주식 거래 주문을 처리하는 엔드포인트
     * 이 메서드는 POST 요청을 통해 사용자가 제공한 해외 주식 거래 주문을 처리합니다.
     * @param request 요청 객체로, 해외 주식 거래 주문에 필요한 매개변수를 포함합니다.
     * @param headers 요청 헤더로, 인증 정보와 같은 중요한 메타 데이터를 포함합니다.
     * @return 해외 주식 거래 주문 처리 결과를 포함한 응답을 반환합니다.
     */
    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> overseasStockTradingOrder(
            @RequestBody OsTradingOrderRequest request,
            @RequestHeader HttpHeaders headers
    ) {
        // 서비스 계층에서 해외 주식 거래 주문을 처리하고, 처리 결과를 반환
        return osTradingOrderService.overseasStockTradingOrder(request, headers);
    }
}
