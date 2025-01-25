package afterwork.millionaire.api.overseas.futureoption.service.tradingaccount;

import afterwork.millionaire.api.overseas.futureoption.dto.tradingaccount.OfTradingOrderRequest;
import afterwork.millionaire.config.ApiProperties;
import afterwork.millionaire.util.WebClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OfTradingOrderService {

    private final ApiProperties apiProperties;

    public Mono<ResponseEntity<Map<String, Object>>> overseasFutureOptionTradingOrder(OfTradingOrderRequest request, HttpHeaders headers) {
            return WebClientUtils.sendPostRequest(apiProperties.getOverseasFutureoptionTradingOrder(), headers, request);
    }

}
