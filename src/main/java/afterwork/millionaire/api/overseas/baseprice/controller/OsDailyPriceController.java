package afterwork.millionaire.api.overseas.baseprice.controller;

import afterwork.millionaire.api.overseas.baseprice.dto.OsDailyPriceRequest;
import afterwork.millionaire.api.overseas.baseprice.service.OsDailyPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * OsDailyPriceController
 * 이 컨트롤러는 해외 주식의 일별 가격 정보를 조회하는 API 엔드포인트를 제공합니다.
 * 사용자가 제공한 인증 정보와 주식 종목에 대한 세부 사항을 기반으로 일별 가격 데이터를 반환합니다.
 */
@RestController
@RequestMapping("/overseas/overseas-stock/dailyprice")
@RequiredArgsConstructor
public class OsDailyPriceController {

    private final OsDailyPriceService osDailyPriceService;

    /**
     * 해외 주식 일별 가격 조회를 위한 엔드포인트
     * 이 메서드는 GET 요청을 통해 해외 주식의 일별 가격을 조회하는 기능을 제공합니다.
     * @param request 요청 객체로, 해외 주식의 가격을 조회하는 데 필요한 매개변수들을 포함합니다.
     * @param headers 요청 헤더로, 인증 정보와 같은 중요한 메타 데이터를 포함합니다.
     * @return 해외 주식 일별 가격 데이터를 포함한 응답을 반환합니다.
     */
    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockPrice(
            @ModelAttribute OsDailyPriceRequest request,
            @RequestHeader HttpHeaders headers
    ) {
        // 서비스 계층에서 해외 주식 일별 가격을 조회하고, 조회 결과를 반환
        return osDailyPriceService.getOverseasStockDailyPrice(request, headers);
    }
}
