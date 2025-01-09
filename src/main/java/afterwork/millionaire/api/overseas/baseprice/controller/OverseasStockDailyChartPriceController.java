package afterwork.millionaire.api.overseas.baseprice.controller;

import afterwork.millionaire.api.overseas.baseprice.dto.OverseasStockDailyChartPriceRequest;
import afterwork.millionaire.api.overseas.baseprice.service.OverseasStockDailyChartPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * OverseasStockDailyChartPriceController
 * 이 컨트롤러는 해외 주식의 일별 차트 가격을 조회하는 API 엔드포인트를 제공합니다.
 * 사용자가 지정한 날짜 범위와 주기 구분 코드에 따라 해외 주식 차트 가격 데이터를 반환합니다.
 */
@RestController
@RequestMapping("/overseas/overseas-stock/inquire-daily-chartprice")
public class OverseasStockDailyChartPriceController {

    @Autowired
    private OverseasStockDailyChartPriceService overseasStockDailyChartPriceService;

    /**
     * 해외 주식 일별 차트 가격 조회를 위한 엔드포인트
     *
     * @param FID_COND_MRKT_DIV_CODE 시장 구분 코드
     * @param FID_INPUT_ISCD         주식 종목 코드
     * @param FID_INPUT_DATE_1       조회 시작 날짜
     * @param FID_INPUT_DATE_2       조회 종료 날짜
     * @param FID_PERIOD_DIV_CODE    주기 구분 코드
     * @param headers                요청 헤더 (인증 정보 등)
     * @return 해외 주식 차트 가격 데이터를 포함한 응답
     */
    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> inquireDailyChartPrice(
            @RequestParam String FID_COND_MRKT_DIV_CODE,
            @RequestParam String FID_INPUT_ISCD,
            @RequestParam String FID_INPUT_DATE_1,
            @RequestParam String FID_INPUT_DATE_2,
            @RequestParam String FID_PERIOD_DIV_CODE,
            @RequestHeader HttpHeaders headers
    ) {
        // 요청 파라미터를 기반으로 DTO 객체 생성
        OverseasStockDailyChartPriceRequest request = new OverseasStockDailyChartPriceRequest(
                FID_COND_MRKT_DIV_CODE, FID_INPUT_ISCD, FID_INPUT_DATE_1, FID_INPUT_DATE_2, FID_PERIOD_DIV_CODE
        );
        // 서비스 호출하여 일별 차트 가격 조회
        return overseasStockDailyChartPriceService.getOverseasStockDailyChartPrice(request, headers);
    }
}