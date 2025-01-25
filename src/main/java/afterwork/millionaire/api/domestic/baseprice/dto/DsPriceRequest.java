package afterwork.millionaire.api.domestic.baseprice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DsPriceRequest
 * 국내 주식 가격 데이터를 요청하기 위한 DTO 클래스입니다.
 * 주식 가격 조회에 필요한 매개변수를 포함합니다.
 */
@Data
@AllArgsConstructor
public class DsPriceRequest {
    private String fid_cond_mrkt_div_code; // 시장 구분 코드
    private String fid_input_iscd;        // 주식 종목 코드
}
