package afterwork.millionaire.api.overseas.tradingaccount.controller;

import afterwork.millionaire.api.oauth.dto.HashkeyRequest;
import afterwork.millionaire.api.oauth.dto.RealTimeConnectionKeyRequest;
import afterwork.millionaire.api.overseas.tradingaccount.dto.OverseasStockTradingOrderRequest;
import afterwork.millionaire.api.overseas.tradingaccount.service.OverseasStockTradingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/overseas/overseas-stock/trading/order")
public class OverseasStockTradingOrderController {

    @Autowired
    private OverseasStockTradingOrderService overseasStockTradingOrderService;

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> overseasStockTradingOrder(
            @RequestBody OverseasStockTradingOrderRequest request,  // 요청 본문에서 해시 키 요청 정보를 받음
            @RequestHeader HttpHeaders headers  // 요청 헤더에서 앱 키, 시크릿, 콘텐츠 타입을 받음
    ) {
        return overseasStockTradingOrderService.overseasStockTradingOrder(request,headers);
    }
}
