package afterwork.millionaire.api.overseas.tradingaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OverseasStockBuyableAmountRequest {
    private String CANO;
    private String ACNT_PRDT_CD;
    private String OVRS_EXCG_CD;
    private String OVRS_ORD_UNPR;
    private String ITEM_CD;
}
