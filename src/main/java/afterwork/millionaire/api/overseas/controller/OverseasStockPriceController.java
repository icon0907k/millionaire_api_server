package afterwork.millionaire.api.overseas.controller;

import afterwork.millionaire.api.overseas.dto.OverseasStockPriceRequest;
import afterwork.millionaire.api.overseas.service.OverseasStockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/overseas/overseas-stock/price")
public class OverseasStockPriceController {

    private final OverseasStockPriceService overseasStockPriceService;

    @Autowired
    public OverseasStockPriceController(OverseasStockPriceService overseasStockPriceService) {
        this.overseasStockPriceService = overseasStockPriceService;
    }

    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockPrice(
            @RequestParam String AUTH,
            @RequestParam String EXCD,
            @RequestParam String SYMB,
            @RequestHeader HttpHeaders headers
            ) {
        OverseasStockPriceRequest request = new OverseasStockPriceRequest(AUTH, EXCD, SYMB);
        return overseasStockPriceService.getOverseasStockPrice(request, headers);
    }
}
