package afterwork.millionaire.api.overseas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OverseasStockSearchRequest {

    private String AUTH;
    private String EXCD;
    private String CO_YN_PRICECUR;
    private String CO_ST_PRICECUR;
    private String CO_EN_PRICECUR;
    private String CO_YN_RATE;
    private String CO_ST_RATE;
    private String CO_EN_RATE;
    private String CO_YN_VALX;
    private String CO_ST_VALX;
    private String CO_EN_VALX;
    private String CO_YN_SHAR;
    private String CO_ST_SHAR;
    private String CO_EN_SHAR;
    private String CO_YN_VOLUME;
    private String CO_ST_VOLUME;
    private String CO_EN_VOLUME;
    private String CO_YN_AMT;
    private String CO_ST_AMT;
    private String CO_EN_AMT;
    private String CO_YN_EPS;
    private String CO_ST_EPS;
    private String CO_EN_EPS;
    private String CO_YN_PER;
    private String CO_ST_PER;
    private String CO_EN_PER;

    public OverseasStockSearchRequest() {
    }
}
