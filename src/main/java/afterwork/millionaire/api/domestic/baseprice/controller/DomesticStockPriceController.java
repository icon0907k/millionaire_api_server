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
 * 주요 기능:
 * - 사용자가 요청한 시장 구분 코드와 종목 코드를 기반으로 주식 가격 데이터를 조회
 */
@RestController
@RequestMapping("/domestic/domestic-stock/price")
public class DomesticStockPriceController {

    @Autowired
    private DomesticStockPriceService domesticStockPriceService;

    /**
     * 주식 가격 조회 API
     * 요청받은 시장 구분 코드와 종목 코드를 사용하여 해당 주식의 가격 데이터를 조회합니다.
     * @param request  사용자가 제공한 요청 데이터를 포함하는 객체
     *                 (시장 구분 코드, 종목 코드 등 포함)
     * @param headers  요청 헤더 (인증 정보 포함 가능)
     * @return 조회된 주식 가격 데이터를 포함한 Mono<ResponseEntity>
     */
    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getDomesticStockPrice(
            @ModelAttribute DomesticStockPriceRequest request,
            @RequestHeader HttpHeaders headers
    ) {
        // 서비스 계층에서 요청 데이터를 처리하고 결과 반환
        return domesticStockPriceService.getDomesticStockPrice(request, headers);
    }
}
