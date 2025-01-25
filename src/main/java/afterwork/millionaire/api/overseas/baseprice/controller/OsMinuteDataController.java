package afterwork.millionaire.api.overseas.baseprice.controller;

import afterwork.millionaire.api.overseas.baseprice.dto.OsMinuteDataRequest;
import afterwork.millionaire.api.overseas.baseprice.service.OsMinuteDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * OsMinuteDataController
 * 이 컨트롤러는 해외 주식의 분 단위 데이터와 시간 범위에 따른 데이터를 조회하는 API 엔드포인트를 제공합니다.
 * 주식 종목 및 다양한 파라미터를 기반으로 실시간 분 단위 데이터를 반환합니다.
 */
@RestController
@RequestMapping("/overseas/overseas-stock/inquire-time-itemchartprice")
@RequiredArgsConstructor
public class OsMinuteDataController {

    private final OsMinuteDataService osMinuteDataService;

    /**
     * 해외 주식 분 단위 데이터를 조회하는 엔드포인트
     * 이 메서드는 GET 요청을 통해 해외 주식의 분 단위 데이터를 조회하는 기능을 제공합니다.
     * @param request 요청 객체로, 해외 주식의 분 단위 데이터를 조회하는 데 필요한 매개변수들을 포함합니다.
     * @param headers 요청 헤더로, 인증 정보와 같은 중요한 메타 데이터를 포함합니다.
     * @return 해외 주식 분 단위 데이터를 포함한 응답을 반환합니다.
     */
    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockMinuteData(
            @ModelAttribute OsMinuteDataRequest request,
            @RequestHeader HttpHeaders headers
    ){
        // 서비스 계층에서 해외 주식 분 단위 데이터를 조회하고, 조회 결과를 반환
        return osMinuteDataService.getOverseasStockMinuteData(request, headers);
    }

    /**
     * 해외 주식 시간 범위에 따른 데이터를 조회하는 엔드포인트
     * 이 메서드는 GET 요청을 통해 주어진 시간 범위에 따른 해외 주식 데이터를 조회합니다.
     * @param TIME1   시작 시간 (시작 시점)
     * @param TIME2   종료 시간 (끝 시점)
     * @param headers 요청 헤더 (인증 정보 등)
     * @return 해외 주식 시간 범위 데이터를 포함한 응답을 반환합니다.
     */
    @GetMapping("/timerang-query")
    public Mono<ResponseEntity<Map<String, Object>>> OverseasStockTimeRangeQuery(
            @ModelAttribute OsMinuteDataRequest request,
            @RequestParam String TIME1, // 시작 시간
            @RequestParam String TIME2, // 종료 시간
            @RequestHeader HttpHeaders headers // 요청 헤더를 바인딩 (예: 인증 정보)
    ){
        request.setNMIN("10"); // NMIN을 10으로 설정하여 주기적으로 조회
        // 서비스 계층에서 주어진 시간 범위에 따른 해외 주식 데이터를 조회하고, 조회 결과를 반환
        return osMinuteDataService.getOverseasStockTimeRangeQuery(request, headers, TIME1, TIME2);
    }
}
