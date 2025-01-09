package afterwork.millionaire.api.overseas.baseprice.controller;

import afterwork.millionaire.api.overseas.baseprice.dto.OverseasStockSearchRequest;
import afterwork.millionaire.api.overseas.baseprice.service.OverseasStockSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * OverseasStockSearchController
 * 이 컨트롤러는 해외 주식 검색을 위한 API 엔드포인트를 제공합니다.
 * 주식 검색에 필요한 다양한 파라미터를 기반으로 검색 데이터를 반환합니다.
 */
@RestController
@RequestMapping("/overseas/overseas-stock/search")
public class OverseasStockSearchController {

    @Autowired
    private OverseasStockSearchService overseasStockSearchService;

    /**
     * 해외 주식 검색을 위한 엔드포인트
     *
     * @param AUTH                  인증 정보
     * @param EXCD                  거래소 코드
     * @param CO_YN_PRICECUR        가격 커런시 사용 여부
     * @param CO_ST_PRICECUR        가격 커런시 시작 값
     * @param CO_EN_PRICECUR        가격 커런시 종료 값
     * @param CO_YN_RATE            환율 사용 여부
     * @param CO_ST_RATE            환율 시작 값
     * @param CO_EN_RATE            환율 종료 값
     * @param CO_YN_VALX            가치 사용 여부
     * @param CO_ST_VALX            가치 시작 값
     * @param CO_EN_VALX            가치 종료 값
     * @param CO_YN_SHAR            주식 수 사용 여부
     * @param CO_ST_SHAR            주식 수 시작 값
     * @param CO_EN_SHAR            주식 수 종료 값
     * @param CO_YN_VOLUME          거래량 사용 여부
     * @param CO_ST_VOLUME          거래량 시작 값
     * @param CO_EN_VOLUME          거래량 종료 값
     * @param CO_YN_AMT             금액 사용 여부
     * @param CO_ST_AMT             금액 시작 값
     * @param CO_EN_AMT             금액 종료 값
     * @param CO_YN_EPS             주당순이익 사용 여부
     * @param CO_ST_EPS             주당순이익 시작 값
     * @param CO_EN_EPS             주당순이익 종료 값
     * @param CO_YN_PER             PER 사용 여부
     * @param CO_ST_PER             PER 시작 값
     * @param CO_EN_PER             PER 종료 값
     * @param headers               요청 헤더 (인증 정보 등)
     * @return 해외 주식 검색 데이터를 포함한 응답
     */
    @GetMapping
    public Mono<ResponseEntity<Map<String, Object>>> getOverseasStockSearch(
            @RequestParam String AUTH,
            @RequestParam String EXCD,
            @RequestParam String CO_YN_PRICECUR,
            @RequestParam String CO_ST_PRICECUR,
            @RequestParam String CO_EN_PRICECUR,
            @RequestParam String CO_YN_RATE,
            @RequestParam String CO_ST_RATE,
            @RequestParam String CO_EN_RATE,
            @RequestParam String CO_YN_VALX,
            @RequestParam String CO_ST_VALX,
            @RequestParam String CO_EN_VALX,
            @RequestParam String CO_YN_SHAR,
            @RequestParam String CO_ST_SHAR,
            @RequestParam String CO_EN_SHAR,
            @RequestParam String CO_YN_VOLUME,
            @RequestParam String CO_ST_VOLUME,
            @RequestParam String CO_EN_VOLUME,
            @RequestParam String CO_YN_AMT,
            @RequestParam String CO_ST_AMT,
            @RequestParam String CO_EN_AMT,
            @RequestParam String CO_YN_EPS,
            @RequestParam String CO_ST_EPS,
            @RequestParam String CO_EN_EPS,
            @RequestParam String CO_YN_PER,
            @RequestParam String CO_ST_PER,
            @RequestParam String CO_EN_PER,
            @RequestHeader HttpHeaders headers
    ) {
        // 요청 파라미터를 기반으로 DTO 객체 생성
        OverseasStockSearchRequest request = new OverseasStockSearchRequest();
        request.setAUTH(AUTH);
        request.setEXCD(EXCD);
        request.setCO_YN_PRICECUR(CO_YN_PRICECUR);
        request.setCO_ST_PRICECUR(CO_ST_PRICECUR);
        request.setCO_EN_PRICECUR(CO_EN_PRICECUR);
        request.setCO_YN_RATE(CO_YN_RATE);
        request.setCO_ST_RATE(CO_ST_RATE);
        request.setCO_EN_RATE(CO_EN_RATE);
        request.setCO_YN_VALX(CO_YN_VALX);
        request.setCO_ST_VALX(CO_ST_VALX);
        request.setCO_EN_VALX(CO_EN_VALX);
        request.setCO_YN_SHAR(CO_YN_SHAR);
        request.setCO_ST_SHAR(CO_ST_SHAR);
        request.setCO_EN_SHAR(CO_EN_SHAR);
        request.setCO_YN_VOLUME(CO_YN_VOLUME);
        request.setCO_ST_VOLUME(CO_ST_VOLUME);
        request.setCO_EN_VOLUME(CO_EN_VOLUME);
        request.setCO_YN_AMT(CO_YN_AMT);
        request.setCO_ST_AMT(CO_ST_AMT);
        request.setCO_EN_AMT(CO_EN_AMT);
        request.setCO_YN_EPS(CO_YN_EPS);
        request.setCO_ST_EPS(CO_ST_EPS);
        request.setCO_EN_EPS(CO_EN_EPS);
        request.setCO_YN_PER(CO_YN_PER);
        request.setCO_ST_PER(CO_ST_PER);
        request.setCO_EN_PER(CO_EN_PER);

        // 서비스 호출하여 해외 주식 검색 데이터 조회
        return overseasStockSearchService.getOverseasStockSearchData(request, headers);
    }
}
