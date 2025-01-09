package afterwork.millionaire.api.overseas.baseprice.controller;

import afterwork.millionaire.api.overseas.baseprice.dto.OverseasStockMinuteDataRequest;
import afterwork.millionaire.api.overseas.baseprice.service.OverseasStockMinuteDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * OverseasStockMinuteDataController
 * 이 컨트롤러는 해외 주식의 분 단위 데이터와 시간 범위에 따른 데이터를 조회하는 API 엔드포인트를 제공합니다.
 * 주식 종목 및 다양한 파라미터를 기반으로 실시간 분 단위 데이터를 반환합니다.
 */
@RestController
@RequestMapping("/overseas/overseas-stock/inquire-time-itemchartprice")
public class OverseasStockMinuteDataController {

    @Autowired
    private OverseasStockMinuteDataService overseasStockMinuteDataService;

    /**
     * 해외 주식 분 단위 데이터를 조회하는 엔드포인트
     *
     * @param AUTH    인증 정보
     * @param EXCD    거래소 코드
     * @param SYMB    주식 종목 코드
     * @param NMIN    조회 시작 분
     * @param PINC    증가 값
     * @param NEXT    다음 데이터 여부
     * @param NREC    조회 개수
     * @param FILL    채우기 옵션
     * @param KEYB    키 값
     * @param headers 요청 헤더 (인증 정보 등)
     * @return 해외 주식 분 단위 데이터를 포함한 응답
     */
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
        // 요청 파라미터를 기반으로 DTO 객체 생성
        OverseasStockMinuteDataRequest request = new OverseasStockMinuteDataRequest(AUTH, EXCD, SYMB,
                NMIN, PINC, NEXT, NREC, FILL, KEYB);
        // 서비스 호출하여 해외 주식 분 단위 데이터 조회
        return overseasStockMinuteDataService.getOverseasStockMinuteData(request, headers);
    }

    /**
     * 해외 주식 시간 범위에 따른 데이터를 조회하는 엔드포인트
     *
     * @param AUTH    인증 정보
     * @param EXCD    거래소 코드
     * @param SYMB    주식 종목 코드
     * @param PINC    증가 값
     * @param NEXT    다음 데이터 여부
     * @param NREC    조회 개수
     * @param FILL    채우기 옵션
     * @param KEYB    키 값
     * @param TIME1   시작 시간
     * @param TIME2   종료 시간
     * @param headers 요청 헤더 (인증 정보 등)
     * @return 해외 주식 시간 범위 데이터를 포함한 응답
     */
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
        // 요청 파라미터를 기반으로 DTO 객체 생성
        OverseasStockMinuteDataRequest request = new OverseasStockMinuteDataRequest(AUTH, EXCD, SYMB,
                "10", PINC, NEXT, NREC, FILL, KEYB);
        // 서비스 호출하여 시간 범위 데이터 조회
        return overseasStockMinuteDataService.getOverseasStockTimeRangeQuery(request, headers, TIME1, TIME2);
    }
}
