package afterwork.millionaire.api.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * HashkeyRequest
 *
 * 해시 키 요청에 필요한 파라미터들을 정의한 클래스입니다.
 * 이 클래스는 특정 주문에 대한 정보를 포함하여 해시 키를 생성하는 데 사용됩니다.
 */
@Data
@AllArgsConstructor
public class HashkeyRequest {

    // 주문 처리 구분 코드 (예: 신규 주문, 수정 주문 등)
    private String ORD_PRCS_DVSN_CD;
    // 계좌 번호 (고객의 계좌번호)
    private String CANO;
    // 상품 코드 (주문할 상품의 코드)
    private String ACNT_PRDT_CD;
    // 매도/매수 구분 코드 (주문 유형: 매도 또는 매수)
    private String SLL_BUY_DVSN_CD;
    // 단축 상품 코드 (상품 코드의 축약형)
    private String SHTN_PDNO;
    // 주문 수량 (주문할 수량)
    private String ORD_QTY;
    // 단가 (주문 단가)
    private String UNIT_PRICE;
    // 예시: 미정 (해당 필드는 비어있을 수 있음)
    private String NMPR_TYPE_CD;
    // 예시: 미정 (해당 필드는 비어있을 수 있음)
    private String KRX_NMPR_CNDT_CD;
    // 연락처 (고객의 연락처)
    private String CTAC_TLNO;
    // 예시: 미정 (해당 필드는 비어있을 수 있음)
    private String FUOP_ITEM_DVSN_CD;
    // 주문 구분 코드 (주문 유형: 신규 주문, 수정 주문 등)
    private String ORD_DVSN_CD;

    public HashkeyRequest() {

    }
}
