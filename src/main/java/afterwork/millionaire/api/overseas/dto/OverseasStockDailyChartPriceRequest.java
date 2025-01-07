package afterwork.millionaire.api.overseas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OverseasStockDailyChartPriceRequest {
    private String FID_COND_MRKT_DIV_CODE;
    private String FID_INPUT_ISCD;
    private String FID_INPUT_DATE_1;
    private String FID_INPUT_DATE_2;
    private String FID_PERIOD_DIV_CODE;
}
