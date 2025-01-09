package afterwork.millionaire.api.overseas.baseprice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * OverseasStockDailyPriceRequest
 * 해외 주식 일별 가격 데이터를 요청하기 위한 DTO 클래스입니다.
 * 주식 가격 조회에 필요한 매개변수를 포함합니다.
 */
@Data
@AllArgsConstructor
public class OverseasStockDailyPriceRequest {
    private String AUTH;  // 인증 코드
    private String EXCD;  // 거래소 코드
    private String SYMB;  // 주식 종목 코드
    private String GUBN;  // 구분 코드
    private String BYMD;  // 조회 날짜 (YYYYMMDD 형식)
    private String MODP;  // 수정 코드
}
