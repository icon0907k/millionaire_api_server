package afterwork.millionaire.config;

import afterwork.millionaire.filter.ApiTypeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FilterConfig
 * 애플리케이션의 필터 설정을 담당하는 클래스입니다.
 * `ApiTypeFilter`를 필터로 등록하고, 모든 URL 패턴에 대해 필터를 적용합니다.
 */
@Configuration
public class FilterConfig {

    private final ApiTypeFilter apiTypeFilter;

    // 생성자 주입으로 ApiTypeFilter를 주입받기
    @Autowired
    public FilterConfig(ApiTypeFilter apiTypeFilter) {
        this.apiTypeFilter = apiTypeFilter;
    }

    /**
     * ApiTypeFilter를 필터로 등록하고, 필터 실행 순서를 설정합니다.
     * @return FilterRegistrationBean<ApiTypeFilter> 필터 등록 정보
     */
    @Bean
    public FilterRegistrationBean<ApiTypeFilter> apiTypeFilterRegistration() {
        FilterRegistrationBean<ApiTypeFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(apiTypeFilter);  // 생성자 주입 받은 ApiTypeFilter 사용
        registrationBean.addUrlPatterns("/*"); // 필터를 적용할 URL 패턴
        registrationBean.setOrder(1); // 필터 실행 순서
        return registrationBean;
    }
}
