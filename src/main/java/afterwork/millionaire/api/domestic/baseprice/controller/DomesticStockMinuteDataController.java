package afterwork.millionaire.api.domestic.baseprice.controller;

import afterwork.millionaire.api.domestic.baseprice.dto.DomesticStockMinuteDataRequest;
import afterwork.millionaire.api.domestic.baseprice.service.DomesticStockMinuteDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * DomesticStockMinuteDataController
 * 이 컨트롤러는 국내 주식 분봉 데이터를 처리하는 API 엔드포인트를 제공합니다.
 * 주식의 분봉 데이터를 조회하거나 시간 범위별로 데이터를 비교하는 기능을 수행합니다.
 */
@RestController
@RequestMapping("/domestic/domestic-stock/inquire-time-itemchartprice")
public class DomesticStockMinuteDataController {

    @Autowired
    private DomesticStockMinuteDataService domesticStockMinuteDataService;

    /**
     * 국내 주식 분봉 데이터를 조회하는 엔드포인트입니다.
     * 주어진 매개변수를 기반으로 데이터를 필터링하여 반환합니다.
     *
     * @param FID_ETC_CLS_CODE    추가 분류 코드
     * @param FID_COND_MRKT_DIV_CODE 시장 구분 코드
     * @param FID_INPUT_ISCD      주식 종목 코드
     * @param FID_INPUT_HOUR_1    시간 필터
     * @param FID_PW_DATA_INCU_YN 추가 데이터 포함 여부
     * @param headers             요청 헤더
     * @return 필터링된 분봉 데이터 결과를 포함한 응답
     */
    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getDomesticStockMinuteData(
            @RequestParam String FID_ETC_CLS_CODE,
            @RequestParam String FID_COND_MRKT_DIV_CODE,
            @RequestParam String FID_INPUT_ISCD,
            @RequestParam String FID_INPUT_HOUR_1,
            @RequestParam String FID_PW_DATA_INCU_YN,
            @RequestHeader HttpHeaders headers
    ) {
        // 요청 매개변수를 기반으로 요청 객체 생성
        DomesticStockMinuteDataRequest request = new DomesticStockMinuteDataRequest(
                FID_ETC_CLS_CODE, FID_COND_MRKT_DIV_CODE, FID_INPUT_ISCD,
                FID_INPUT_HOUR_1, FID_PW_DATA_INCU_YN
        );

        // 서비스 계층 호출을 통해 분봉 데이터를 조회하고 반환
        return domesticStockMinuteDataService.getDomesticStockMinuteData(request, headers);
    }

    /**
     * 두 시간 범위의 주식 데이터를 비교하는 엔드포인트입니다.
     * 두 시간대의 open 값을 비교하여 차이를 계산하고 상태를 반환합니다.
     *
     * @param FID_ETC_CLS_CODE    추가 분류 코드
     * @param FID_COND_MRKT_DIV_CODE 시장 구분 코드
     * @param FID_INPUT_ISCD      주식 종목 코드
     * @param FID_PW_DATA_INCU_YN 추가 데이터 포함 여부
     * @param TIME1               첫 번째 시간
     * @param TIME2               두 번째 시간
     * @param headers             요청 헤더
     * @return 두 시간대의 데이터를 비교한 결과를 포함한 응답
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
        // 첫 번째와 두 번째 시간에 해당하는 요청 객체 생성
        DomesticStockMinuteDataRequest request1 = new DomesticStockMinuteDataRequest(
                FID_ETC_CLS_CODE, FID_COND_MRKT_DIV_CODE, FID_INPUT_ISCD, TIME1, FID_PW_DATA_INCU_YN
        );
        DomesticStockMinuteDataRequest request2 = new DomesticStockMinuteDataRequest(
                FID_ETC_CLS_CODE, FID_COND_MRKT_DIV_CODE, FID_INPUT_ISCD, TIME2, FID_PW_DATA_INCU_YN
        );

        // 서비스 계층 호출을 통해 두 시간대 데이터를 비교하고 결과 반환
        return domesticStockMinuteDataService.getDomesticStockTimeRangeQuery(request1, request2, headers, TIME1, TIME2);
    }
}
