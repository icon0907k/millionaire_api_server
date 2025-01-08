package afterwork.millionaire.api.overseas.service;

import afterwork.millionaire.api.overseas.dto.OverseasStockSearchRequest;
import afterwork.millionaire.config.ApiProperties;
import afterwork.millionaire.util.WebClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class OverseasStockSearchService {

    // API 설정을 담고 있는 ApiProperties 의존성 주입
    @Autowired
    private ApiProperties apiProperties;


    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockSearchData(OverseasStockSearchRequest request, HttpHeaders headers) {
        return WebClientUtils.sendGetRequest(apiProperties.getOverseasInquireSearch(), headers, request);
    }
}
