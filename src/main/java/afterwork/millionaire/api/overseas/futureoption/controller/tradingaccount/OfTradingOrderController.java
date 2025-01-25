package afterwork.millionaire.api.overseas.futureoption.controller.tradingaccount;

import afterwork.millionaire.api.overseas.futureoption.dto.tradingaccount.OfTradingOrderRequest;
import afterwork.millionaire.api.overseas.futureoption.service.tradingaccount.OfTradingOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/overseas/futureoption/trading/order")
@RequiredArgsConstructor
public class OfTradingOrderController {

    @Autowired
    private OfTradingOrderService ofTradingOrderService;

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> overseasFutureOptionTradingOrder(
            @RequestBody OfTradingOrderRequest request,
            @RequestHeader HttpHeaders headers
    ) {
        return ofTradingOrderService.overseasFutureOptionTradingOrder(request, headers);
    }
}
