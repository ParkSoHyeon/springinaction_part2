package spittr.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

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

    @Bean MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("file:///etc/spittr/messages");
        messageSource.setCacheSeconds(10);

        return messageSource;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tiles = new TilesConfigurer();

        // "/WEB-INF/**/tiles.xml"
        tiles.setDefinitions(new String[] {
                "/WEB-INF/layout/tiles.xml"
        });

        tiles.setCheckRefresh(true);

        return tiles;
    }

    @Bean
    public ViewResolver viewResolver() {
        return new TilesViewResolver();
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
