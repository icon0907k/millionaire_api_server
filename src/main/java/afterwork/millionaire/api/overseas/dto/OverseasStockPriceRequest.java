package afterwork.millionaire.api.overseas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OverseasStockPriceRequest {
    private String AUTH;
    private String EXCD;
    private String SYMB;
}
