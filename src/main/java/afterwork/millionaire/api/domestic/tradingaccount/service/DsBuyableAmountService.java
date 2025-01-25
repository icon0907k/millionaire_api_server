package afterwork.millionaire.api.domestic.tradingaccount.service;

import afterwork.millionaire.api.domestic.tradingaccount.dto.DsBuyableAmountRequest;
import afterwork.millionaire.config.ApiProperties;
import afterwork.millionaire.util.WebClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * DsBuyableAmountService
 * 국내 주식 거래 가능한 금액을 조회하는 서비스 클래스입니다.
 * 해당 서비스는 주식 거래 가능한 금액 정보를 API를 통해 조회합니다.
 */
@Service
@RequiredArgsConstructor
public class DsBuyableAmountService {

    private final ApiProperties apiProperties;

    /**
     * 해외 주식 거래 가능한 금액을 조회하는 메서드입니다.
     * @param request   요청 데이터 (CANO, ACNT_PRDT_CD 등)
     * @param headers   요청 헤더 (인증 관련 정보 포함)
     * @return          거래 가능한 금액을 포함한 응답 데이터
     */
    public Mono<ResponseEntity<Map<String, Object>>> getDomesticStockBuyableAmount(DsBuyableAmountRequest request, HttpHeaders headers) {
        return WebClientUtils.sendGetRequest(apiProperties.getDomesticTradingInquirePsamount(), headers, request);
    }
}
