package afterwork.millionaire.api.domestic.tradingaccount.controller;

import afterwork.millionaire.api.domestic.tradingaccount.dto.DomesticStockBuyableAmountRequest;
import afterwork.millionaire.api.domestic.tradingaccount.service.DomesticStockBuyableAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * DomesticStockBuyableAmountController
 * 국내 주식 매수 가능 금액을 조회하는 API를 제공하는 컨트롤러 클래스입니다.
 */
@RestController
@RequestMapping("/domestic/domestic-stock/trading/inquire-psamount")
public class DomesticStockBuyableAmountController {

    @Autowired
    private DomesticStockBuyableAmountService domesticStockBuyableAmountService;

    /**
     * 국내 주식 매수 가능 금액을 조회하는 엔드포인트입니다.
     *
     * @param CANO                 계좌번호
     * @param ACNT_PRDT_CD         계좌상품코드
     * @param PDNO                 종목번호
     * @param ORD_UNPR             주문단가
     * @param ORD_DVSN             주문구분
     * @param OVRS_ICLD_YN         해외포함여부
     * @param CMA_EVLU_AMT_ICLD_YN CMA평가금액포함여부
     * @param headers              요청 헤더 (인증 및 기타 정보 포함)
     * @return 매수 가능 금액 데이터를 포함한 응답
     */
    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockBuyableAmount(
            @RequestParam String CANO,                 // 계좌번호
            @RequestParam String ACNT_PRDT_CD,         // 계좌상품코드
            @RequestParam String PDNO,                 // 종목번호
            @RequestParam String ORD_UNPR,             // 주문단가
            @RequestParam String ORD_DVSN,             // 주문구분
            @RequestParam String OVRS_ICLD_YN,         // 해외포함여부
            @RequestParam String CMA_EVLU_AMT_ICLD_YN, // CMA평가금액포함여부
            @RequestHeader HttpHeaders headers         // 요청 헤더
    ) {
        // 요청 데이터를 DTO로 변환
        DomesticStockBuyableAmountRequest request = new DomesticStockBuyableAmountRequest(
                CANO, ACNT_PRDT_CD, PDNO, ORD_UNPR, ORD_DVSN, OVRS_ICLD_YN, CMA_EVLU_AMT_ICLD_YN
        );

        // 서비스 호출을 통해 매수 가능 금액 데이터 조회
        return domesticStockBuyableAmountService.getDomesticStockBuyableAmount(request, headers);
    }
}
