package spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    //DispatcherServlet이 애플리케이션 컨텍스트를 WebConfig 설정 클래스에서 정의된 빈으로 로딩하기를 요청하도록 되어있음
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfig.class};
    }

    //DispatcherServlet이 매핑되기 위한 패스 지정
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
