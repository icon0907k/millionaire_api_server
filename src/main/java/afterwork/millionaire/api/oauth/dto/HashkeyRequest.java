package afterwork.millionaire.api.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * HashkeyRequest
 * 해시 키 요청에 필요한 주문 정보를 담고 있는 클래스입니다.
 */
@Data
@AllArgsConstructor
public class HashkeyRequest {

    private String ORD_PRCS_DVSN_CD;  // 주문 처리 구분 코드 (신규, 수정 등)
    private String CANO;              // 계좌 번호
    private String ACNT_PRDT_CD;      // 상품 코드
    private String SLL_BUY_DVSN_CD;   // 매도/매수 구분 코드
    private String SHTN_PDNO;         // 단축 상품 코드
    private String ORD_QTY;           // 주문 수량
    private String UNIT_PRICE;        // 단가
    private String NMPR_TYPE_CD;      // (비어 있을 수 있음)
    private String KRX_NMPR_CNDT_CD;  // (비어 있을 수 있음)
    private String CTAC_TLNO;         // 연락처
    private String FUOP_ITEM_DVSN_CD; // (비어 있을 수 있음)
    private String ORD_DVSN_CD;       // 주문 구분 코드 (신규, 수정 등)

}
