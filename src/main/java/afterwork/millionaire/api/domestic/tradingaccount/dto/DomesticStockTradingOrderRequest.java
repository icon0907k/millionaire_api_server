package afterwork.millionaire.api.domestic.tradingaccount.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DomesticStockTradingOrderRequest
 * 국내 주식 거래 주문을 요청하기 위한 DTO 클래스입니다.
 * 주식 거래 주문에 필요한 매개변수를 포함합니다.
 */
@Data
@AllArgsConstructor
public class DomesticStockTradingOrderRequest {

    @JsonProperty("CANO")
    private String CANO;               // 계좌 번호
    @JsonProperty("ACNT_PRDT_CD")
    private String ACNT_PRDT_CD;       // 계좌 상품 코드
    @JsonProperty("PDNO")
    private String PDNO;               // 거래소 코드
    @JsonProperty("ORD_DVSN")
    private String ORD_DVSN;           // 상품 번호
    @JsonProperty("ORD_QTY")
    private String ORD_QTY;            // 주문 수량
    @JsonProperty("ORD_UNPR")
    private String ORD_UNPR;           // 주문 단가
}
