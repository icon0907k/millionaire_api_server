package afterwork.millionaire.api.overseas.tradingaccount.service;

import afterwork.millionaire.api.overseas.tradingaccount.dto.OsBuyableAmountRequest;
import afterwork.millionaire.config.ApiProperties;
import afterwork.millionaire.util.WebClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * OsBuyableAmountService
 * 해외 주식 구매 가능 금액을 조회하는 서비스 클래스입니다.
 * 주어진 요청 데이터를 기반으로 API 호출을 통해 해외 주식의 구매 가능한 금액 정보를 반환합니다.
 */
@Service
@RequiredArgsConstructor
public class OsBuyableAmountService {

    private final ApiProperties apiProperties;

    /**
     * 해외 주식 구매 가능 금액을 조회하는 메서드
     * @param request 요청 데이터 (주식 계좌 정보 및 기타 필요한 파라미터)
     * @param headers 요청 헤더 (인증 정보 등)
     * @return 해외 주식 구매 가능 금액을 포함한 응답
     */
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockBuyableAmount(
            OsBuyableAmountRequest request,
            HttpHeaders headers) {

        // WebClient를 이용하여 GET 요청을 보내고, 응답을 Mono 형태로 반환
        return WebClientUtils.sendGetRequest(apiProperties.getOverseasTradingInquirePsamount(), headers, request);
    }
}
