package afterwork.millionaire.api.domestic.baseprice.controller;

import afterwork.millionaire.api.domestic.baseprice.dto.DomesticStockPriceRequest;
import afterwork.millionaire.api.domestic.baseprice.service.DomesticStockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * DomesticStockPriceController
 * 이 컨트롤러는 국내 주식 가격 데이터를 처리하는 API 엔드포인트를 제공합니다.
 * 사용자가 요청한 주식 종목의 가격 데이터를 조회하는 역할을 합니다.
 */
@RestController
@RequestMapping("/domestic/domestic-stock/price")
public class DomesticStockPriceController {

    @Autowired
    private DomesticStockPriceService domesticStockPriceService;

    /**
     * 국내 주식 가격 데이터를 조회하는 엔드포인트입니다.
     * 주어진 시장 구분 코드와 종목 코드를 기반으로 데이터를 조회합니다.
     *
     * @param fid_cond_mrkt_div_code 시장 구분 코드
     * @param fid_input_iscd         주식 종목 코드
     * @param headers                요청 헤더
     * @return 주식 가격 데이터를 포함한 응답
     */
    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getDomesticStockPrice(
            @RequestParam String fid_cond_mrkt_div_code,  // 시장 구분 코드
            @RequestParam String fid_input_iscd,          // 주식 종목 코드
            @RequestHeader HttpHeaders headers            // 요청 헤더
    ) {
        // 요청 매개변수를 기반으로 요청 객체 생성
        DomesticStockPriceRequest request = new DomesticStockPriceRequest(fid_cond_mrkt_div_code, fid_input_iscd);

        // 서비스 계층 호출을 통해 주식 가격 데이터를 조회하고 반환
        return domesticStockPriceService.getDomesticStockPrice(request, headers);
    }
}
