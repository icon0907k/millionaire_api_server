package afterwork.millionaire.api.overseas.tradingaccount.service;

import afterwork.millionaire.api.overseas.tradingaccount.dto.OsTradingOrderRequest;
import afterwork.millionaire.config.ApiProperties;
import afterwork.millionaire.util.WebClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * OsTradingOrderService
 * 해외 주식 거래 주문을 처리하는 서비스 클래스입니다.
 * 주어진 요청 데이터를 기반으로 API 호출을 통해 해외 주식 거래 주문을 전송합니다.
 */
@Service
@RequiredArgsConstructor
public class OsTradingOrderService {

    private final ApiProperties apiProperties;
    /**
     * 해외 주식 거래 주문을 처리하는 메서드
     * @param request 해외 주식 거래 주문에 필요한 데이터
     * @param headers 요청 헤더 (인증 정보 등)
     * @return 거래 주문 결과를 포함한 응답
     */
    public Mono<ResponseEntity<Map<String, Object>>> overseasStockTradingOrder(
            OsTradingOrderRequest request,
            HttpHeaders headers) {

        // WebClient를 이용하여 POST 요청을 보내고, 응답을 Mono 형태로 반환
        return WebClientUtils.sendPostRequest(apiProperties.getOverseasTradingOrder(), headers, request);
    }
}
