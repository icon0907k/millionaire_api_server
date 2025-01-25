package afterwork.millionaire.api.domestic.tradingaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DsBuyableAmountRequest
 * 국내 주식 구매 가능 금액을 요청하기 위한 DTO 클래스입니다.
 * 주식 구매 가능 금액 조회에 필요한 매개변수를 포함합니다.
 */
@Data
@AllArgsConstructor
public class DsBuyableAmountRequest {
    private String CANO;                // 계좌 번호
    private String ACNT_PRDT_CD;        // 계좌 상품 코드
    private String PDNO;                // 상품 번호
    private String ORD_UNPR;            // 주문 단가
    private String ORD_DVSN;            // 주문 구분
    private String OVRS_ICLD_YN;        // 해외 포함 여부 (Y/N)
    private String CMA_EVLU_AMT_ICLD_YN; // CMA 평가 금액 포함 여부 (Y/N)
}
