package afterwork.millionaire.api.domestic.tradingaccount.service;

import afterwork.millionaire.api.domestic.tradingaccount.dto.DomesticStockTradingOrderRequest;
import afterwork.millionaire.config.ApiProperties;
import afterwork.millionaire.util.WebClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class DomesticStockTradingOrderService {

    @Autowired
    private ApiProperties apiProperties; // API 설정을 불러오기 위한 의존성

    /**
     * 국내 주식 거래 주문을 처리하는 메서드입니다.
     *
     * @param request 주문 요청 데이터
     * @param headers 요청 헤더 정보
     * @return 거래 주문 결과를 포함한 ResponseEntity
     */
    public Mono<ResponseEntity<Map<String, Object>>> domesticStockTradingOrder(DomesticStockTradingOrderRequest request, HttpHeaders headers) {
        // 외부 API로 GET 요청을 보내는 유틸리티 메서드 호출
        return WebClientUtils.sendPostRequest(apiProperties.getDomesticTradingOrder(), headers, request);
    }
}
