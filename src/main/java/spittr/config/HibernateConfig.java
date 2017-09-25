package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfig {
    //XML 매핑 파일을 이용한 퍼시스턴스 도메인 객체 설정(하이버네이트3)
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(DataSource dataSource) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setMappingResources(new String[]{"Spitter.hbm.xml"});

        Properties properties = new Properties();
        properties.setProperty("dialect", "org.hibernate.dialect.H2Dialect");

        localSessionFactoryBean.setHibernateProperties(properties);

        return localSessionFactoryBean;
    }

    //애너테이션을 이용한 퍼시스턴스 도메인 객체 설정(하이버네이트3)
    @Bean
    public AnnotationSessionFactoryBean annotationSessionFactoryBean(DataSource dataSource) {
        AnnotationSessionFactoryBean annotationSessionFactoryBean = new AnnotationSessionFactoryBean();
        annotationSessionFactoryBean.setDataSource(dataSource);
        annotationSessionFactoryBean.setPackagesToScan(new String[]{"com.spittr.domain"});

        Properties properties = new Properties();
        properties.setProperty("dialect", "org.hibernate.dialect.H2Dialect");

        annotationSessionFactoryBean.setHibernateProperties(properties);

        return annotationSessionFactoryBean;
    }

    //애너테이션을 이용한 퍼시스턴스 도메인 객체 설정(하이버네이트4)
    @Bean
    public org.springframework.orm.hibernate4.LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource) {
        org.springframework.orm.hibernate4.LocalSessionFactoryBean localSessionFactoryBean = new org.springframework.orm.hibernate4.LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan(new String[] {"com.spittr.domain"});

        Properties properties = new Properties();
        properties.setProperty("dialect", "org.hibernate.dialect.H2Dialect");

        localSessionFactoryBean.setHibernateProperties(properties);

        return localSessionFactoryBean;
    }
}