package afterwork.millionaire.api.domestic.baseprice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DomesticStockMinuteDataRequest
 * 국내 주식 분봉 데이터를 요청하기 위한 DTO 클래스입니다.
 * 주식 데이터 요청에 필요한 매개변수를 포함합니다.
 */
@Data
@AllArgsConstructor
public class DomesticStockMinuteDataRequest {
    private String FID_ETC_CLS_CODE;       // 기타 클래스 코드
    private String FID_COND_MRKT_DIV_CODE; // 시장 구분 코드
    private String FID_INPUT_ISCD;         // 주식 종목 코드
    private String FID_INPUT_HOUR_1;       // 요청 시간
    private String FID_PW_DATA_INCU_YN;    // 데이터 포함 여부 (Y/N)
}
