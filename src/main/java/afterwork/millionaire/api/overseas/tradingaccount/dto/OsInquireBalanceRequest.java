package afterwork.millionaire.api.overseas.tradingaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OsInquireBalanceRequest {
    private String CANO;  // 계좌 번호
    private String ACNT_PRDT_CD;  // 계좌 상품 코드
    private String OVRS_EXCG_CD;
    private String TR_CRCY_CD;
    private String CTX_AREA_FK200;
    private String CTX_AREA_NK200;
}
