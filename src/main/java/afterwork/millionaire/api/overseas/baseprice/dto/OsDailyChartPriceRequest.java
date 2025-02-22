package afterwork.millionaire.api.overseas.baseprice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * OsDailyChartPriceRequest
 * 해외 주식 일별 차트 가격 데이터를 요청하기 위한 DTO 클래스입니다.
 * 주식 차트 가격 조회에 필요한 매개변수를 포함합니다.
 */
@Data
@AllArgsConstructor
public class OsDailyChartPriceRequest {
    private String FID_COND_MRKT_DIV_CODE;  // 시장 구분 코드
    private String FID_INPUT_ISCD;          // 주식 종목 코드
    private String FID_INPUT_DATE_1;        // 조회 시작 날짜
    private String FID_INPUT_DATE_2;        // 조회 종료 날짜
    private String FID_PERIOD_DIV_CODE;     // 주기 구분 코드
}
