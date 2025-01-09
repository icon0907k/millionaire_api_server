package afterwork.millionaire.api.overseas.baseprice.controller;

import afterwork.millionaire.api.overseas.baseprice.dto.OverseasStockDailyPriceRequest;
import afterwork.millionaire.api.overseas.baseprice.service.OverseasStockDailyPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * OverseasStockDailyPriceController
 * 이 컨트롤러는 해외 주식의 일별 가격 정보를 조회하는 API 엔드포인트를 제공합니다.
 * 사용자가 제공한 인증 정보와 주식 종목에 대한 세부 사항을 기반으로 일별 가격 데이터를 반환합니다.
 */
@RestController
@RequestMapping("/overseas/overseas-stock/dailyprice")
public class OverseasStockDailyPriceController {

    @Autowired
    private OverseasStockDailyPriceService overseasStockDailyPriceService;

    /**
     * 해외 주식 일별 가격 조회를 위한 엔드포인트
     *
     * @param AUTH    인증 정보
     * @param EXCD    거래소 코드
     * @param SYMB    주식 종목 코드
     * @param GUBN    구분 코드
     * @param BYMD    조회 날짜
     * @param MODP    수정 코드
     * @param headers 요청 헤더 (인증 정보 등)
     * @return 해외 주식 일별 가격 데이터를 포함한 응답
     */
    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockPrice(
            @RequestParam String AUTH,
            @RequestParam String EXCD,
            @RequestParam String SYMB,
            @RequestParam String GUBN,
            @RequestParam String BYMD,
            @RequestParam String MODP,
            @RequestHeader HttpHeaders headers
    ) {
        // 요청 파라미터를 기반으로 DTO 객체 생성
        OverseasStockDailyPriceRequest request = new OverseasStockDailyPriceRequest(AUTH, EXCD, SYMB, GUBN, BYMD, MODP);
        // 서비스 호출하여 해외 주식 일별 가격 조회
        return overseasStockDailyPriceService.getOverseasStockDailyPrice(request, headers);
    }
}
