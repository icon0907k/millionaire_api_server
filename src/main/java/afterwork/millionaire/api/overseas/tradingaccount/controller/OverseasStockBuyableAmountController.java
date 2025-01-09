package afterwork.millionaire.api.overseas.tradingaccount.controller;

import afterwork.millionaire.api.overseas.tradingaccount.dto.OverseasStockBuyableAmountRequest;
import afterwork.millionaire.api.overseas.tradingaccount.service.OverseasStockBuyableAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/overseas/overseas-stock/trading/inquire-psamount")
public class OverseasStockBuyableAmountController {

    @Autowired
    private OverseasStockBuyableAmountService overseasStockBuyableAmountService;

    @GetMapping
    public Mono<ResponseEntity<Map<String,Object>>> getOverseasStockBuyableAmount(
            @RequestParam String CANO,
            @RequestParam String ACNT_PRDT_CD,
            @RequestParam String OVRS_EXCG_CD,
            @RequestParam String OVRS_ORD_UNPR,
            @RequestParam String ITEM_CD,
            @RequestHeader HttpHeaders headers
            ){
        OverseasStockBuyableAmountRequest request = new OverseasStockBuyableAmountRequest(CANO, ACNT_PRDT_CD, OVRS_EXCG_CD,
                OVRS_ORD_UNPR, ITEM_CD);
        return overseasStockBuyableAmountService.getOverseasStockBuyableAmount(request,headers);
    }
}
