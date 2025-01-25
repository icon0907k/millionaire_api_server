package afterwork.millionaire.api.overseas.baseprice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * OsMinuteDataRequest
 * 해외 주식 분봉 데이터를 요청하기 위한 DTO 클래스입니다.
 * 주식 분봉 데이터 조회에 필요한 매개변수를 포함합니다.
 */
@Data
@AllArgsConstructor
public class OsMinuteDataRequest {
    private String AUTH;  // 인증 코드
    private String EXCD;  // 거래소 코드
    private String SYMB;  // 주식 종목 코드
    private String NMIN;  // 분봉 시간
    private String PINC;  // 가격 간격
    private String NEXT;  // 다음 데이터 여부
    private String NREC;  // 데이터 수
    private String FILL;  // 빈 데이터 처리 여부
    private String KEYB;  // 키값
}
