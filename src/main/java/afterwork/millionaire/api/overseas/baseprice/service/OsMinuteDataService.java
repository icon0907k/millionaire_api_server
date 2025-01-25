package afterwork.millionaire.api.overseas.baseprice.service;

import afterwork.millionaire.api.overseas.baseprice.dto.OsMinuteDataRequest;
import afterwork.millionaire.config.ApiProperties;
import afterwork.millionaire.util.DateUtil;
import afterwork.millionaire.util.WebClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OsMinuteDataService
 * 해외 주식 분봉 데이터를 처리하는 서비스 클래스입니다.
 * 주식 분봉 데이터 요청과 두 시간 간의 open 값 비교를 처리합니다.
 */
@Service
@RequiredArgsConstructor
public class OsMinuteDataService {

    private final ApiProperties apiProperties;

    /**
     * 해외 주식 분봉 데이터를 요청하는 메소드입니다.
     * @param request 요청에 필요한 파라미터를 포함하는 DTO
     * @param headers HTTP 요청 헤더
     * @return API 응답 결과를 포함하는 Mono 객체
     */
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockMinuteData(OsMinuteDataRequest request, HttpHeaders headers) {
        // 외부 API에 GET 요청을 보내고 응답을 Mono 형태로 반환
        return WebClientUtils.sendGetRequest(apiProperties.getOverseasInquireTimeItemchartprice(), headers, request);
    }

    /**
     * 해외 주식 시간 범위 쿼리 요청을 보내고 두 시간의 open 값을 비교하는 메소드입니다.
     * @param request 요청에 필요한 파라미터를 포함하는 DTO
     * @param headers HTTP 요청 헤더
     * @param targetTime1 첫 번째 시간 (HHMMSS 형식)
     * @param targetTime2 두 번째 시간 (HHMMSS 형식)
     * @return 두 시간 간의 open 값 차이를 계산한 결과를 포함하는 Mono 객체
     */
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockTimeRangeQuery(OsMinuteDataRequest request, HttpHeaders headers, String targetTime1, String targetTime2) {
        // 주식 분봉 데이터를 가져오고, 두 시간의 open 값을 비교하는 메소드 호출
        return compareOpenValues(WebClientUtils.sendGetRequest(apiProperties.getOverseasInquireTimeItemchartprice(), headers, request), targetTime1, targetTime2);
    }

    /**
     * 두 시간의 open 값을 비교하여 결과를 반환하는 메소드입니다.
     * @param responseMono API 응답을 포함하는 Mono 객체
     * @param targetTime1 첫 번째 시간 (HHMMSS 형식)
     * @param targetTime2 두 번째 시간 (HHMMSS 형식)
     * @return 두 시간 간의 open 값 차이를 계산한 결과를 포함하는 Mono 객체
     */
    public Mono<ResponseEntity<Map<String, Object>>> compareOpenValues(Mono<ResponseEntity<Map<String, Object>>> responseMono, String targetTime1, String targetTime2) {
        return responseMono.map(responseEntity -> {
            Map<String, Object> responseMap = responseEntity.getBody();
            Map<String, Object> result = new HashMap<>();

            if (responseMap == null) {
                result.put("error", "응답 데이터가 없습니다.");
                return ResponseEntity.ok(result);
            }

            Object output2Object = responseMap.get("output2");

            // List<Map<String, Object>>로 안전하게 변환
            List<Map<String, Object>> output2List = (List<Map<String, Object>>) output2Object;

            // 특정 날짜 정의
            String targetDateStr1 = DateUtil.getFormattedDate(true);  // 첫 번째 날짜 ex("20250107")
            String targetDateStr2 = DateUtil.getFormattedDate(false);  // 두 번째 날짜 ex("20250108")

            Double openValue1 = null;
            Double openValue2 = null;

            // output2의 각 항목에서 kymd와 khms를 추출하여 해당 날짜, 시간에 맞는 open 값을 찾기
            for (Map<String, Object> item : output2List) {
                String kymd = (String) item.get("kymd");
                String khms = (String) item.get("khms");
                double openValue = Double.parseDouble((String) item.get("open"));

                if (kymd.equals(targetDateStr1) && khms.equals(targetTime1)) {
                    openValue1 = openValue;
                }

                if (kymd.equals(targetDateStr2) && khms.equals(targetTime2)) {
                    openValue2 = openValue;
                }
            }

            // 두 값이 모두 존재하면 차이를 계산하여 결과 반환
            if (openValue1 != null && openValue2 != null) {
                double difference = openValue1 - openValue2;
                result.put("difference", String.format("%.2f", difference));

                if (openValue1.equals(openValue2)) {
                    result.put("status", "3");  // 차이가 0일 경우 "3"
                    result.put("openValue2", String.valueOf(openValue2));
                    result.put("openValue1", String.valueOf(openValue1));
                } else {
                    result.put("status", (difference < 0) ? "1" : "2");  // 차이가 음수일 경우 1, 양수일 경우 0
                    result.put("openValue2", String.valueOf(openValue2));
                    result.put("openValue1", String.valueOf(openValue1));
                }
            } else {
                result.put("error", "주어진 날짜와 시간에 해당하는 open 값을 찾을 수 없습니다.");
            }

            return ResponseEntity.ok(result);  // 결과를 JSON 형태로 반환
        });
    }
}
