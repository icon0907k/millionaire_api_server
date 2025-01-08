package afterwork.millionaire.api.overseas.controller;

import afterwork.millionaire.api.overseas.dto.OverseasStockMinuteDataRequest;
import afterwork.millionaire.api.overseas.service.OverseasStockMinuteDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/overseas/overseas-stock/inquire-time-itemchartprice")
public class OverseasStockMinuteDataController {

    private final OverseasStockMinuteDataService overseasStockMinuteDataService;

    @Autowired
    public OverseasStockMinuteDataController(OverseasStockMinuteDataService overseasStockMinuteDataService) {
        this.overseasStockMinuteDataService = overseasStockMinuteDataService;
    }

    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockMinuteData(
            @RequestParam String AUTH,
            @RequestParam String EXCD,
            @RequestParam String SYMB,
            @RequestParam String NMIN,
            @RequestParam String PINC,
            @RequestParam String NEXT,
            @RequestParam String NREC,
            @RequestParam String FILL,
            @RequestParam String KEYB,
            @RequestHeader HttpHeaders headers
    ){
        OverseasStockMinuteDataRequest request = new OverseasStockMinuteDataRequest(AUTH, EXCD, SYMB,
                NMIN, PINC, NEXT, NREC, FILL, KEYB);
        return overseasStockMinuteDataService.getOverseasStockMinuteData(request,headers);
    }

    @GetMapping("/timerang-query")
    public Mono<ResponseEntity<Map<String, Object>>> OverseasStockTimeRangeQuery(
            @RequestParam String AUTH,
            @RequestParam String EXCD,
            @RequestParam String SYMB,
            @RequestParam String PINC,
            @RequestParam String NEXT,
            @RequestParam String NREC,
            @RequestParam String FILL,
            @RequestParam String KEYB,
            @RequestParam String TIME1,
            @RequestParam String TIME2,
            @RequestHeader HttpHeaders headers
    ){
        OverseasStockMinuteDataRequest request = new OverseasStockMinuteDataRequest(AUTH, EXCD, SYMB,
                "10", PINC, NEXT, NREC, FILL, KEYB);
        return overseasStockMinuteDataService.getOverseasStockTimeRangeQuery(request,headers,TIME1,TIME2);
    }
}
