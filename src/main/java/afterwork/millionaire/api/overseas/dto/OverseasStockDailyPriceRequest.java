package afterwork.millionaire.api.overseas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OverseasStockDailyPriceRequest {
    private String AUTH;
    private String EXCD;
    private String SYMB;
    private String GUBN;
    private String BYMD;
    private String MODP;
}
