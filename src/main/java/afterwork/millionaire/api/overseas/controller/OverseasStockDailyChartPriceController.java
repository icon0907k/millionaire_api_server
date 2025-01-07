package afterwork.millionaire.api.overseas.controller;

import afterwork.millionaire.api.overseas.dto.OverseasStockDailyChartPriceRequest;
import afterwork.millionaire.api.overseas.service.OverseasStockDailyChartPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/overseas/overseas-stock/inquire-daily-chartprice")
public class OverseasStockDailyChartPriceController {
    private final OverseasStockDailyChartPriceService overseasStockDailyChartPriceService;

    @Autowired
    public OverseasStockDailyChartPriceController(OverseasStockDailyChartPriceService overseasStockDailyChartPriceService) {
        this.overseasStockDailyChartPriceService = overseasStockDailyChartPriceService;
    }

    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> inquireDailyChartPrice(
            @RequestParam String FID_COND_MRKT_DIV_CODE,
            @RequestParam String FID_INPUT_ISCD,
            @RequestParam String FID_INPUT_DATE_1,
            @RequestParam String FID_INPUT_DATE_2,
            @RequestParam String FID_PERIOD_DIV_CODE,
            @RequestHeader HttpHeaders headers
    ) {
        OverseasStockDailyChartPriceRequest request = new OverseasStockDailyChartPriceRequest(FID_COND_MRKT_DIV_CODE,
                FID_INPUT_ISCD, FID_INPUT_DATE_1, FID_INPUT_DATE_2, FID_PERIOD_DIV_CODE);
        return overseasStockDailyChartPriceService.getOverseasStockDailyChartPrice(request, headers);
    }
}
