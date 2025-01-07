package afterwork.millionaire.api.overseas.service;

import afterwork.millionaire.api.overseas.dto.OverseasStockPriceRequest;
import afterwork.millionaire.config.ApiProperties;
import afterwork.millionaire.util.WebClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class OverseasStockPriceService {
    // API 설정을 담고 있는 ApiProperties 의존성 주입
    @Autowired
    private ApiProperties apiProperties;

    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockPrice(OverseasStockPriceRequest request, HttpHeaders headers) {
        return WebClientUtils.sendGetRequest(apiProperties.getOverseasPrice(), headers, request);
    }
}
