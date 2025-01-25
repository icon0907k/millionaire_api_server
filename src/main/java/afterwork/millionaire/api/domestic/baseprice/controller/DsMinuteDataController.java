package afterwork.millionaire.api.domestic.baseprice.controller;

import afterwork.millionaire.api.domestic.baseprice.dto.DsMinuteDataRequest;
import afterwork.millionaire.api.domestic.baseprice.service.DsMinuteDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * DsMinuteDataController
 * 이 컨트롤러는 국내 주식 분봉 데이터를 처리하는 API 엔드포인트를 제공합니다.
 * 주요 기능:
 * 1. 분봉 데이터를 조회하여 반환
 * 2. 두 시간 범위의 데이터를 비교하여 상태를 반환
 */
@RestController
@RequestMapping("/domestic/domestic-stock/inquire-time-itemchartprice")
@RequiredArgsConstructor
public class DsMinuteDataController {

    private final DsMinuteDataService dsMinuteDataService;

    /**
     * 분봉 데이터 조회 API
     * 주어진 매개변수를 기반으로 국내 주식 분봉 데이터를 필터링하여 반환합니다.
     * @param request  요청 파라미터를 포함한 객체 (주식 코드, 시간, 시장 구분 등)
     * @param headers  요청 헤더 (인증 토큰 등 포함 가능)
     * @return 필터링된 분봉 데이터를 포함한 Mono<ResponseEntity>
     */
    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getDomesticStockMinuteData(
            @ModelAttribute DsMinuteDataRequest request,
            @RequestHeader HttpHeaders headers
    ) {
        // 서비스 계층에서 데이터 처리 로직 실행 및 반환
        return dsMinuteDataService.getDomesticStockMinuteData(request, headers);
    }

    /**
     * 시간 범위 데이터 비교 API
     * 두 시간 범위의 데이터를 비교하여 상태를 반환합니다.
     * 주요 비교 항목은 open 값입니다.
     * @param FID_ETC_CLS_CODE      추가 분류 코드 (예: 섹터 또는 카테고리)
     * @param FID_COND_MRKT_DIV_CODE 시장 구분 코드 (예: 코스피, 코스닥 등)
     * @param FID_INPUT_ISCD        주식 종목 코드
     * @param FID_PW_DATA_INCU_YN   추가 데이터 포함 여부 (예: true/false)
     * @param TIME1                 첫 번째 비교 시간
     * @param TIME2                 두 번째 비교 시간
     * @param headers               요청 헤더 (인증 정보 포함)
     * @return 두 시간 범위의 데이터를 비교한 결과를 포함한 Mono<ResponseEntity>
     */
    @GetMapping("/timerang-query")
    public Mono<ResponseEntity<Map<String, Object>>> DomesticStockTimeRangeQuery(
            @RequestParam String FID_ETC_CLS_CODE,
            @RequestParam String FID_COND_MRKT_DIV_CODE,
            @RequestParam String FID_INPUT_ISCD,
            @RequestParam String FID_PW_DATA_INCU_YN,
            @RequestParam String TIME1,
            @RequestParam String TIME2,
            @RequestHeader HttpHeaders headers
    ) {
        // 첫 번째 시간의 요청 객체 생성
        DsMinuteDataRequest request1 = new DsMinuteDataRequest(
                FID_ETC_CLS_CODE, FID_COND_MRKT_DIV_CODE, FID_INPUT_ISCD, TIME1, FID_PW_DATA_INCU_YN
        );

        // 두 번째 시간의 요청 객체 생성
        DsMinuteDataRequest request2 = new DsMinuteDataRequest(
                FID_ETC_CLS_CODE, FID_COND_MRKT_DIV_CODE, FID_INPUT_ISCD, TIME2, FID_PW_DATA_INCU_YN
        );

        // 서비스 계층에서 두 시간대 데이터를 비교하고 결과 반환
        return dsMinuteDataService.getDomesticStockTimeRangeQuery(request1, request2, headers, TIME1, TIME2);
    }
}
