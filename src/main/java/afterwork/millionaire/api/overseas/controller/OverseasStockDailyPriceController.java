package afterwork.millionaire.api.overseas.controller;

import afterwork.millionaire.api.overseas.dto.OverseasStockDailyPriceRequest;
import afterwork.millionaire.api.overseas.service.OverseasStockDailyPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/overseas/overseas-stock/dailyprice")
public class OverseasStockDailyPriceController {

    private final OverseasStockDailyPriceService overseasStockDailyPriceService;

    @Autowired
    public OverseasStockDailyPriceController(OverseasStockDailyPriceService overseasStockDailyPriceService) {
        this.overseasStockDailyPriceService = overseasStockDailyPriceService;
    }

    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockPrice(
            @RequestParam String AUTH,
            @RequestParam String EXCD,
            @RequestParam String SYMB,
            @RequestParam String GUBN,
            @RequestParam String BYMD,
            @RequestParam String MODP,
            @RequestHeader HttpHeaders headers
    ) {
        OverseasStockDailyPriceRequest request = new OverseasStockDailyPriceRequest(AUTH, EXCD, SYMB, GUBN, BYMD, MODP);
        return overseasStockDailyPriceService.getOverseasStockDailyPrice(request, headers);
    }
}
