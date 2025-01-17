package afterwork.millionaire.api.overseas.tradingaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * OverseasStockBuyableAmountRequest
 * 해외 주식 매수 가능한 금액을 조회하기 위한 요청 데이터 클래스입니다.
 * 해당 클래스는 주식 거래를 위한 필수 매개변수를 포함하고 있습니다.
 */
@Data
@AllArgsConstructor
public class OverseasStockBuyableAmountRequest {
    private String CANO;  // 계좌 번호
    private String ACNT_PRDT_CD;  // 계좌 상품 코드
    private String OVRS_EXCG_CD;  // 해외 거래소 코드
    private String OVRS_ORD_UNPR;  // 해외 주식 주문 단가
    private String ITEM_CD;  // 주식 종목 코드
}
