package afterwork.millionaire.api.domestic.baseprice.service;

import afterwork.millionaire.api.domestic.baseprice.dto.DsMinuteDataRequest;
import afterwork.millionaire.config.ApiProperties;
import afterwork.millionaire.util.WebClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DsMinuteDataService
 * 국내 주식 분봉 데이터를 처리하는 서비스 클래스입니다.
 * 데이터 요청 및 두 시점 간의 비교 기능을 제공합니다.
 */
@Service
@RequiredArgsConstructor
public class DsMinuteDataService {

    private final ApiProperties apiProperties;

    /**
     * 두 시점의 open 값을 비교하여 결과를 반환하는 메서드입니다.
     * @param responseMono1 첫 번째 시간에 대한 응답 데이터
     * @param responseMono2 두 번째 시간에 대한 응답 데이터
     * @param targetTime1   첫 번째 대상 시간
     * @param targetTime2   두 번째 대상 시간
     * @return 두 open 값의 차이와 상태를 포함한 응답 데이터
     */
    public Mono<ResponseEntity<Map<String, Object>>> compareOpenValues(
            Mono<ResponseEntity<Map<String, Object>>> responseMono1,
            Mono<ResponseEntity<Map<String, Object>>> responseMono2,
            String targetTime1, String targetTime2) {

        return Mono.zip(responseMono1, responseMono2)
                .map(tuple -> {
                    ResponseEntity<Map<String, Object>> responseEntity1 = tuple.getT1();
                    ResponseEntity<Map<String, Object>> responseEntity2 = tuple.getT2();

                    Map<String, Object> responseMap1 = responseEntity1.getBody();
                    Map<String, Object> responseMap2 = responseEntity2.getBody();
                    Map<String, Object> result = new HashMap<>();

                    // 응답 데이터가 없을 경우 에러 반환
                    if (responseMap1 == null || responseMap2 == null) {
                        result.put("error", "응답 데이터가 없습니다.");
                        return ResponseEntity.ok(result);
                    }

                    // 첫 번째 시점의 open 값 추출
                    Double openValue1 = extractOpenValue(responseMap1, targetTime1);
                    // 두 번째 시점의 open 값 추출
                    Double openValue2 = extractOpenValue(responseMap2, targetTime2);

                    // 두 open 값이 존재할 경우 차이 계산 및 결과 설정
                    if (openValue1 != null && openValue2 != null) {
                        double difference = openValue1 - openValue2;
                        result.put("difference", String.format("%.2f", difference));

                        if (openValue1.equals(openValue2)) {
                            result.put("status", "3");  // 두 값이 동일한 경우 상태 "3"
                        } else {
                            result.put("status", (difference < 0) ? "1" : "2");  // 차이에 따른 상태 설정
                        }
                        result.put("openValue1", String.valueOf(openValue1));
                        result.put("openValue2", String.valueOf(openValue2));
                    } else {
                        result.put("error", "주어진 날짜와 시간에 해당하는 stck_oprc 값을 찾을 수 없습니다.");
                    }

                    return ResponseEntity.ok(result);
                });
    }

    /**
     * 주어진 응답 데이터에서 특정 시간에 해당하는 open 값을 추출합니다.
     * @param responseMap 응답 데이터 맵
     * @param targetTime  대상 시간
     * @return 추출된 open 값 또는 null
     */
    private Double extractOpenValue(Map<String, Object> responseMap, String targetTime) {
        Object output2Object = responseMap.get("output2");

        // output2를 List<Map<String, Object>>로 변환
        if (output2Object instanceof List) {
            List<Map<String, Object>> output2List = (List<Map<String, Object>>) output2Object;

            for (Map<String, Object> item : output2List) {
                String khms = (String) item.get("stck_cntg_hour");
                double openValue = Double.parseDouble((String) item.get("stck_oprc"));

                // 대상 시간과 일치하는 경우 open 값 반환
                if (khms.equals(targetTime)) {
                    return openValue;
                }
            }
        }
        return null;
    }

    /**
     * 국내 주식 분봉 데이터를 요청합니다.
     * @param request 요청 DTO
     * @param headers 요청 헤더
     * @return 주식 분봉 데이터 응답
     */
    public Mono<ResponseEntity<Map<String, Object>>> getDomesticStockMinuteData(DsMinuteDataRequest request, HttpHeaders headers) {
        return WebClientUtils.sendGetRequest(apiProperties.getDomesticInquireTimeItemchartprice(), headers, request);
    }

    /**
     * 두 시점 간의 분봉 데이터를 비교하는 요청을 처리합니다.
     * @param request1 첫 번째 시점의 요청 데이터
     * @param request2 두 번째 시점의 요청 데이터
     * @param headers  요청 헤더
     * @param time1    첫 번째 시간
     * @param time2    두 번째 시간
     * @return 두 시점 간의 비교 결과
     */
    public Mono<ResponseEntity<Map<String, Object>>> getDomesticStockTimeRangeQuery(
            DsMinuteDataRequest request1,
            DsMinuteDataRequest request2,
            HttpHeaders headers,
            String time1,
            String time2) {
        return compareOpenValues(
                WebClientUtils.sendGetRequest(apiProperties.getDomesticInquireTimeItemchartprice(), headers, request1),
                WebClientUtils.sendGetRequest(apiProperties.getDomesticInquireTimeItemchartprice(), headers, request2),
                time1, time2
        );
    }
}
