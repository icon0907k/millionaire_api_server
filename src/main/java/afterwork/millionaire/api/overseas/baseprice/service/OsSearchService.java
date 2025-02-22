package afterwork.millionaire.api.overseas.baseprice.service;

import afterwork.millionaire.api.overseas.baseprice.dto.OsSearchRequest;
import afterwork.millionaire.config.ApiProperties;
import afterwork.millionaire.util.WebClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * OsSearchService
 * 해외 주식 검색 데이터를 처리하는 서비스 클래스입니다.
 * 주식 검색 결과를 요청하는 기능을 제공합니다.
 */
@Service
@RequiredArgsConstructor
public class OsSearchService {

    private final ApiProperties apiProperties;

    /**
     * 해외 주식 검색 데이터를 요청하는 메소드입니다.
     * @param request 요청에 필요한 파라미터를 포함하는 DTO
     * @param headers HTTP 요청 헤더
     * @return API 응답 결과를 포함하는 Mono 객체
     */
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockSearchData(OsSearchRequest request, HttpHeaders headers) {
        // 외부 API에 GET 요청을 보내고 응답을 Mono 형태로 반환
        return WebClientUtils.sendGetRequest(apiProperties.getOverseasInquireSearch(), headers, request);
    }
}
