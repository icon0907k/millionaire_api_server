package afterwork.millionaire.api.overseas.tradingaccount.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * OsTradingOrderRequest
 * 해외 주식 거래 주문을 위한 요청 데이터 클래스입니다.
 * 주식 거래를 위한 필수 매개변수를 포함하고 있습니다.
 */
@Data
@AllArgsConstructor
public class OsTradingOrderRequest {
    @JsonProperty("CANO")
    private String CANO;  // 계좌 번호
    @JsonProperty("ACNT_PRDT_CD")
    private String ACNT_PRDT_CD;  // 계좌 상품 코드
    @JsonProperty("OVRS_EXCG_CD")
    private String OVRS_EXCG_CD;  // 해외 거래소 코드
    @JsonProperty("PDNO")
    private String PDNO;  // 주식 종목 코드
    @JsonProperty("ORD_QTY")
    private String ORD_QTY;  // 주문 수량
    @JsonProperty("OVRS_ORD_UNPR")
    private String OVRS_ORD_UNPR;  // 해외 주식 주문 단가
    @JsonProperty("ORD_SVR_DVSN_CD")
    private String ORD_SVR_DVSN_CD;  // 주문 서비스 구분 코드
    @JsonProperty("ORD_DVSN")
    private String ORD_DVSN;  // 주문 구분 코드
}
