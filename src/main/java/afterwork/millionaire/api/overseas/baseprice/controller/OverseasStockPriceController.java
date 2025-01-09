package afterwork.millionaire.api.overseas.baseprice.controller;

import afterwork.millionaire.api.overseas.baseprice.dto.OverseasStockPriceRequest;
import afterwork.millionaire.api.overseas.baseprice.service.OverseasStockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * OverseasStockPriceController
 * 이 컨트롤러는 해외 주식 가격을 조회하는 API 엔드포인트를 제공합니다.
 * 주식 종목과 거래소 코드, 인증 정보를 기반으로 실시간 주식 가격 데이터를 반환합니다.
 */
@RestController
@RequestMapping("/overseas/overseas-stock/price")
public class OverseasStockPriceController {

    @Autowired
    private OverseasStockPriceService overseasStockPriceService;

    /**
     * 해외 주식 가격을 조회하는 엔드포인트
     *
     * @param AUTH    인증 정보
     * @param EXCD    거래소 코드
     * @param SYMB    주식 종목 코드
     * @param headers 요청 헤더 (인증 정보 등)
     * @return 해외 주식 가격 데이터를 포함한 응답
     */
    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockPrice(
            @RequestParam String AUTH,
            @RequestParam String EXCD,
            @RequestParam String SYMB,
            @RequestHeader HttpHeaders headers
    ) {
        // 요청 파라미터를 기반으로 DTO 객체 생성
        OverseasStockPriceRequest request = new OverseasStockPriceRequest(AUTH, EXCD, SYMB);
        // 서비스 호출하여 해외 주식 가격 조회
        return overseasStockPriceService.getOverseasStockPrice(request, headers);
    }
}
