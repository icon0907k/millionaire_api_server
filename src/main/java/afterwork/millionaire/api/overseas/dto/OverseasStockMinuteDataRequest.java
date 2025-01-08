package afterwork.millionaire.api.overseas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OverseasStockMinuteDataRequest {
    private String AUTH;
    private String EXCD;
    private String SYMB;
    private String NMIN;
    private String PINC;
    private String NEXT;
    private String NREC;
    private String FILL;
    private String KEYB;
}
