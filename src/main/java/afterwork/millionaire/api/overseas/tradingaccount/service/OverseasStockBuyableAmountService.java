package afterwork.millionaire.api.overseas.tradingaccount.service;

import afterwork.millionaire.api.overseas.tradingaccount.dto.OverseasStockBuyableAmountRequest;
import afterwork.millionaire.config.ApiProperties;
import afterwork.millionaire.util.WebClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
@Service
public class OverseasStockBuyableAmountService {

    @Autowired
    private ApiProperties apiProperties;

    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockBuyableAmount(OverseasStockBuyableAmountRequest request, HttpHeaders headers) {
        return WebClientUtils.sendGetRequest(apiProperties.getOverseasTradingInquirePsamount(), headers, request);
    }
}
