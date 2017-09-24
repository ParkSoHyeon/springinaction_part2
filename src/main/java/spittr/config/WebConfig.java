package spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan("spittr.web")
public class WebConfig  extends WebMvcConfigurerAdapter{
//    @Bean
//    public ViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/views");
//        resolver.setSuffix(".jsp");
//        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
//        resolver.setExposeContextBeansAsAttributes(true);
//
//        return resolver;
//    }

//    @Bean
//    public MessageSource messageSource( ){
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//
//        messageSource.setBasename("messages");
//
//        return messageSource;
//    }

//    @Bean MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//
//        messageSource.setBasename("file:///etc/spittr/messages");
//        messageSource.setCacheSeconds(10);
//
//        return messageSource;
//    }

//    @Bean
//    public TilesConfigurer tilesConfigurer() {
//        TilesConfigurer tiles = new TilesConfigurer();
//
//        // "/WEB-INF/**/tiles.xml"
//        tiles.setDefinitions(new String[] {
//                "/WEB-INF/layout/tiles.xml"
//        });
//
//        tiles.setCheckRefresh(true);
//
//        return tiles;
//    }

//    @Bean
//    public ViewResolver viewResolver() {
//        return new TilesViewResolver();
//    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Autowired
    private ApplicationContext applicationContext;



    @Bean

    public SpringResourceTemplateResolver templateResolver(){

        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();

        templateResolver.setApplicationContext(this.applicationContext);

        templateResolver.setPrefix("/webapp/WEB-INF/templates/"); // HTML 파일 위치

        templateResolver.setSuffix(".html"); // HTML 확장명 사용

        templateResolver.setTemplateMode(TemplateMode.HTML); // HTML5 값은 비권장 됨

        templateResolver.setCacheable(false); // 캐시 사용 안함(사용하면 html 수정시 서버 재기동 필요)

        return templateResolver;

    }



    @Bean

    public SpringTemplateEngine templateEngine(){

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        templateEngine.setTemplateResolver(templateResolver());

        return templateEngine;

    }



    @Bean

    public ThymeleafViewResolver viewResolver(){

        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();

        viewResolver.setCharacterEncoding("UTF-8"); // 한글 깨짐 방지

        viewResolver.setTemplateEngine(templateEngine());

        return viewResolver;

    }
}
