package afterwork.millionaire.api.overseas.controller;

import afterwork.millionaire.api.overseas.dto.OverseasStockSearchRequest;
import afterwork.millionaire.api.overseas.service.OverseasStockSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/overseas/overseas-stock/search")
public class OverseasStockSearchController {

    private final OverseasStockSearchService overseasStockSearchService;

    @Autowired
    public OverseasStockSearchController(OverseasStockSearchService overseasStockSearchService) {
        this.overseasStockSearchService = overseasStockSearchService;
    }

    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockSearch(
            @RequestParam String AUTH,
            @RequestParam String EXCD,
            @RequestParam String CO_YN_PRICECUR,
            @RequestParam String CO_ST_PRICECUR,
            @RequestParam String CO_EN_PRICECUR,
            @RequestParam String CO_YN_RATE,
            @RequestParam String CO_ST_RATE,
            @RequestParam String CO_EN_RATE,
            @RequestParam String CO_YN_VALX,
            @RequestParam String CO_ST_VALX,
            @RequestParam String CO_EN_VALX,
            @RequestParam String CO_YN_SHAR,
            @RequestParam String CO_ST_SHAR,
            @RequestParam String CO_EN_SHAR,
            @RequestParam String CO_YN_VOLUME,
            @RequestParam String CO_ST_VOLUME,
            @RequestParam String CO_EN_VOLUME,
            @RequestParam String CO_YN_AMT,
            @RequestParam String CO_ST_AMT,
            @RequestParam String CO_EN_AMT,
            @RequestParam String CO_YN_EPS,
            @RequestParam String CO_ST_EPS,
            @RequestParam String CO_EN_EPS,
            @RequestParam String CO_YN_PER,
            @RequestParam String CO_ST_PER,
            @RequestParam String CO_EN_PER,
            @RequestHeader HttpHeaders headers
    ) {
        OverseasStockSearchRequest request = new OverseasStockSearchRequest();
        request.setAUTH(AUTH);
        request.setEXCD(EXCD);
        request.setCO_YN_PRICECUR(CO_YN_PRICECUR);
        request.setCO_ST_PRICECUR(CO_ST_PRICECUR);
        request.setCO_EN_PRICECUR(CO_EN_PRICECUR);
        request.setCO_YN_RATE(CO_YN_RATE);
        request.setCO_ST_RATE(CO_ST_RATE);
        request.setCO_EN_RATE(CO_EN_RATE);
        request.setCO_YN_VALX(CO_YN_VALX);
        request.setCO_ST_VALX(CO_ST_VALX);
        request.setCO_EN_VALX(CO_EN_VALX);
        request.setCO_YN_SHAR(CO_YN_SHAR);
        request.setCO_ST_SHAR(CO_ST_SHAR);
        request.setCO_EN_SHAR(CO_EN_SHAR);
        request.setCO_YN_VOLUME(CO_YN_VOLUME);
        request.setCO_ST_VOLUME(CO_ST_VOLUME);
        request.setCO_EN_VOLUME(CO_EN_VOLUME);
        request.setCO_YN_AMT(CO_YN_AMT);
        request.setCO_ST_AMT(CO_ST_AMT);
        request.setCO_EN_AMT(CO_EN_AMT);
        request.setCO_YN_EPS(CO_YN_EPS);
        request.setCO_ST_EPS(CO_ST_EPS);
        request.setCO_EN_EPS(CO_EN_EPS);
        request.setCO_YN_PER(CO_YN_PER);
        request.setCO_ST_PER(CO_ST_PER);
        request.setCO_EN_PER(CO_EN_PER);

        return overseasStockSearchService.getOverseasStockSearchData(request,headers);
    }
}
