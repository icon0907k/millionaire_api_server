package afterwork.millionaire.api.overseas.tradingaccount.service;

import afterwork.millionaire.api.overseas.tradingaccount.dto.OsInquireBalanceRequest;
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
public class OsInquireBalanceService {

    private final ApiProperties apiProperties;

    public Mono<ResponseEntity<Map<String, Object>>> getInquireBalance(OsInquireBalanceRequest request, HttpHeaders headers) {
        return WebClientUtils.sendGetRequest(apiProperties.getOverseasTradingInquireBalance(), headers, request);
    }
}
