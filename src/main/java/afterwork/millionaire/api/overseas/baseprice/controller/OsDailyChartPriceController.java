package afterwork.millionaire.api.overseas.baseprice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * OsDailyChartPriceController
 * 이 컨트롤러는 해외 주식의 일별 차트 가격을 조회하는 API 엔드포인트를 제공합니다.
 * 사용자가 지정한 날짜 범위와 주기 구분 코드에 따라 해외 주식 차트 가격 데이터를 반환합니다.
 */
@RestController
@RequestMapping("/overseas/overseas-stock/inquire-daily-chartprice")
@RequiredArgsConstructor
public class OsDailyChartPriceController {

    private final OsDailyChartPriceService osDailyChartPriceService;

    /**
     * 해외 주식 일별 차트 가격 조회를 위한 엔드포인트
     * 주어진 날짜 범위와 주기 구분 코드에 따라 해외 주식의 일별 차트 가격을 조회합니다.
     * @param request                 요청 객체로서, 시장 구분 코드, 종목 코드, 날짜 범위, 주기 구분 코드 등을 포함
     * @param headers                 요청 헤더 (인증 정보 포함 가능)
     * @return 해외 주식 차트 가격 데이터를 포함한 응답 (일별 가격 정보)
     */
    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> inquireDailyChartPrice(
            @ModelAttribute OsDailyChartPriceRequest request,
            @RequestHeader HttpHeaders headers
    ) {
        // 서비스 계층에서 일별 차트 가격 조회 후 반환
        return osDailyChartPriceService.getOverseasStockDailyChartPrice(request, headers);
    }
}
