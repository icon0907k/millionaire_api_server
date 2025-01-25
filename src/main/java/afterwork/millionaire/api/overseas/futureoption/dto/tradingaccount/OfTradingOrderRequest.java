package afterwork.millionaire.api.overseas.futureoption.dto.tradingaccount;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OfTradingOrderRequest {

    @JsonProperty("CANO")
    private String CANO;  // 계좌 번호
    @JsonProperty("ACNT_PRDT_CD")
    private String ACNT_PRDT_CD;  // 계좌 상품 코드
    @JsonProperty("OVRS_FUTR_FX_PDNO")
    private String OVRS_FUTR_FX_PDNO;
    @JsonProperty("SLL_BUY_DVSN_CD")
    private String SLL_BUY_DVSN_CD;
    @JsonProperty("FM_LQD_USTL_CCNO")
    private String FM_LQD_USTL_CCNO;
    @JsonProperty("PRIC_DVSN_CD")
    private String PRIC_DVSN_CD;
    @JsonProperty("FM_LIMIT_ORD_PRIC")
    private String FM_LIMIT_ORD_PRIC;
    @JsonProperty("FM_ORD_QTY")
    private String FM_ORD_QTY;
    @JsonProperty("FM_LQD_LMT_ORD_PRIC")
    private String FM_LQD_LMT_ORD_PRIC;
    @JsonProperty("FM_LQD_STOP_ORD_PRIC")
    private String FM_LQD_STOP_ORD_PRIC;
    @JsonProperty("CCLD_CNDT_CD")
    private String CCLD_CNDT_CD;
    @JsonProperty("CPLX_ORD_DVSN_CD")
    private String CPLX_ORD_DVSN_CD;
    @JsonProperty("ECIS_RSVN_ORD_YN")
    private String ECIS_RSVN_ORD_YN;
    @JsonProperty("FM_HDGE_ORD_SCRN_YN")
    private String FM_HDGE_ORD_SCRN_YN;
}
