package afterwork.millionaire.api.overseas.tradingaccount.service;

import afterwork.millionaire.api.overseas.tradingaccount.dto.OverseasStockTradingOrderRequest;
import afterwork.millionaire.config.ApiProperties;
import afterwork.millionaire.util.WebClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class OverseasStockTradingOrderService {
    @Autowired
    private ApiProperties apiProperties;

    public Mono<ResponseEntity<Map<String, Object>>> overseasStockTradingOrder(OverseasStockTradingOrderRequest request, HttpHeaders headers) {
        return WebClientUtils.sendPostRequest(apiProperties.getOverseasTradingOrder(), headers, request);
    }
}
