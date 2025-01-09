package afterwork.millionaire.api.overseas.tradingaccount.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OverseasStockTradingOrderRequest {
    @JsonProperty("CANO")
    private String CANO;
    @JsonProperty("ACNT_PRDT_CD")
    private String ACNT_PRDT_CD;
    @JsonProperty("OVRS_EXCG_CD")
    private String OVRS_EXCG_CD;
    @JsonProperty("PDNO")
    private String PDNO;
    @JsonProperty("ORD_QTY")
    private String ORD_QTY;
    @JsonProperty("OVRS_ORD_UNPR")
    private String OVRS_ORD_UNPR;
    @JsonProperty("ORD_SVR_DVSN_CD")
    private String ORD_SVR_DVSN_CD;
    @JsonProperty("ORD_DVSN")
    private String ORD_DVSN;
}
