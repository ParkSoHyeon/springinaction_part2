package spittr.config;

import com.myapp.MyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import java.io.IOException;

@Configuration
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

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] {(Filter) new MyFilter()};
    }

    //멀티파트 요청
//    @Bean
//    public MultipartResolver multipartResolver() throws IOException {
//        return new StandardServletMultipartResolver();
//    }
//
//    @Override
//    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//        //임시 파일 위치 설정, 업로드되는 파일 크기, 전체 요청 크기
//        registration.setMultipartConfig(new MultipartConfigElement("/temp/spittr/uploads", 2097152, 4194304, 0));
//    }

    //서블릿 3.0 이하 멀티파트 요청(멀티 요청의 최대크기 명시하는 방법 x)
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();

        multipartResolver.setUploadTempDir(new FileSystemResource("tmp/spittr/uploads"));

        multipartResolver.setMaxUploadSize(2097152);

        multipartResolver.setMaxInMemorySize(0);

        return multipartResolver;
    }
}
