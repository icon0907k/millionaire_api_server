package afterwork.millionaire.api.overseas.tradingaccount.controller;

import afterwork.millionaire.api.overseas.tradingaccount.dto.OsBuyableAmountRequest;
import afterwork.millionaire.api.overseas.tradingaccount.dto.OsInquireBalanceRequest;
import afterwork.millionaire.api.overseas.tradingaccount.service.OsInquireBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/overseas/overseas-stock/trading/inquire-balance")
@RequiredArgsConstructor
public class OsInquireBalanceController {

    private final OsInquireBalanceService osInquireBalanceService;

    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> inquireBalance(
            @ModelAttribute OsInquireBalanceRequest request,
            @RequestHeader HttpHeaders headers
    ){
        return osInquireBalanceService.getInquireBalance(request, headers);
    }
}
