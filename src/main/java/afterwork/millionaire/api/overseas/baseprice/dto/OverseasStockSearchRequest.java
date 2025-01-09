package afterwork.millionaire.api.overseas.baseprice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OverseasStockSearchRequest
 * 해외 주식 검색 데이터를 요청하기 위한 DTO 클래스입니다.
 * 주식 검색에 필요한 다양한 매개변수를 포함합니다.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OverseasStockSearchRequest {

    private String AUTH;              // 인증 코드
    private String EXCD;              // 거래소 코드
    private String CO_YN_PRICECUR;    // 가격 커런시 포함 여부 (Y/N)
    private String CO_ST_PRICECUR;    // 시작 가격 커런시
    private String CO_EN_PRICECUR;    // 종료 가격 커런시
    private String CO_YN_RATE;        // 환율 포함 여부 (Y/N)
    private String CO_ST_RATE;        // 시작 환율
    private String CO_EN_RATE;        // 종료 환율
    private String CO_YN_VALX;        // 가치 포함 여부 (Y/N)
    private String CO_ST_VALX;        // 시작 가치
    private String CO_EN_VALX;        // 종료 가치
    private String CO_YN_SHAR;        // 주식 포함 여부 (Y/N)
    private String CO_ST_SHAR;        // 시작 주식
    private String CO_EN_SHAR;        // 종료 주식
    private String CO_YN_VOLUME;      // 거래량 포함 여부 (Y/N)
    private String CO_ST_VOLUME;      // 시작 거래량
    private String CO_EN_VOLUME;      // 종료 거래량
    private String CO_YN_AMT;         // 금액 포함 여부 (Y/N)
    private String CO_ST_AMT;         // 시작 금액
    private String CO_EN_AMT;         // 종료 금액
    private String CO_YN_EPS;         // 주당 순이익 포함 여부 (Y/N)
    private String CO_ST_EPS;         // 시작 주당 순이익
    private String CO_EN_EPS;         // 종료 주당 순이익
    private String CO_YN_PER;         // 주가수익비율 포함 여부 (Y/N)
    private String CO_ST_PER;         // 시작 주가수익비율
    private String CO_EN_PER;         // 종료 주가수익비율
}
