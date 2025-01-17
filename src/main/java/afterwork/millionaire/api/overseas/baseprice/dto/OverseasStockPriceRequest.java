package afterwork.millionaire.api.overseas.baseprice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OverseasStockPriceRequest
 * 해외 주식 가격 데이터를 요청하기 위한 DTO 클래스입니다.
 * 주식 가격 조회에 필요한 매개변수를 포함합니다.
 */
@Data
@AllArgsConstructor
public class OverseasStockPriceRequest {
    private String AUTH;  // 인증 코드
    private String EXCD;  // 거래소 코드
    private String SYMB;  // 주식 종목 코드
}
