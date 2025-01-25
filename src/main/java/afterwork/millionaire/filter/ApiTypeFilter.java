package afterwork.millionaire.filter;

import afterwork.millionaire.config.ApiProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * ApiTypeFilter
 * 요청 헤더에서 `API_TYPE` 값을 확인하여 실제 API와 모의 API의 베이스 URL을 설정하는 필터입니다.
 */
@Component
public class ApiTypeFilter extends OncePerRequestFilter {

    private final ApiProperties apiProperties;

    // ApiProperties를 생성자 주입으로 받아옵니다.
    @Autowired
    public ApiTypeFilter(ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
    }

    /**
     * 요청이 들어올 때마다 호출되는 필터 메서드입니다.
     * 요청 헤더에서 API_TYPE 값을 확인하고, 그에 맞는 베이스 URL을 설정합니다.
     * @param request HTTP 요청 객체
     * @param response HTTP 응답 객체
     * @param filterChain 필터 체인
     * @throws ServletException 서블릿 예외
     * @throws IOException 입출력 예외
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 요청 헤더에서 API_TYPE 값을 가져오기
        String apiType = request.getHeader("API_TYPE");

        // API_TYPE 값이 "real"일 경우 실제 API URL을 설정
        if ("real".equalsIgnoreCase(apiType)) {
            apiProperties.setBaseUrl("https://openapi.koreainvestment.com:9443");
        } else  {
            // API_TYPE 값이 "real"이 아닌 경우, mock API URL을 설정
            apiProperties.setBaseUrl("https://openapivts.koreainvestment.com:29443");
        }

        // 필터 체인을 통해 요청을 다음 필터나 컨트롤러로 전달
        filterChain.doFilter(request, response);
    }
}
