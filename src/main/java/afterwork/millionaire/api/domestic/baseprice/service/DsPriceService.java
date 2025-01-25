package afterwork.millionaire.api.domestic.baseprice.service;

import afterwork.millionaire.api.domestic.baseprice.dto.DsPriceRequest;
import afterwork.millionaire.config.ApiProperties;
import afterwork.millionaire.util.WebClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * DsPriceService
 * 국내 주식 가격 데이터를 처리하는 서비스 클래스입니다.
 */
@Service
@RequiredArgsConstructor
public class DsPriceService {

    private final ApiProperties apiProperties;

    /**
     * 국내 주식 가격 데이터를 요청하는 메서드입니다.
     * @param request 요청 데이터 (종목 코드 및 시장 구분 코드)
     * @param headers 요청 헤더 (인증 및 기타 정보 포함)
     * @return 주식 가격 데이터를 포함한 응답
     */
    public Mono<ResponseEntity<Map<String, Object>>> getDomesticStockPrice(DsPriceRequest request, HttpHeaders headers) {
        // WebClientUtils를 사용하여 GET 요청을 전송
        return WebClientUtils.sendGetRequest(apiProperties.getDomesticPrice(), headers, request);
    }
}
