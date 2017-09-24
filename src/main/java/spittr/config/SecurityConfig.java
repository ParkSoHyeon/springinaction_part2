package spittr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import spittr.data.SpitterRepository;
import spittr.security.SpitterUserService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    DataSource dataSource;

    SpitterRepository spitterRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login")
                .and()
                .rememberMe()
                .tokenValiditySeconds(2419200)
                .key("spittrkey")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .logoutUrl("signout")
                .and()
//                .httpBasic()
//                .realmName("Spittr")
                .authorizeRequests()
                .antMatchers("spitter/me").hasRole("SPITTER")//.hasAuthority("ROLE_SPITTER")
                .antMatchers(HttpMethod.POST, "spittles").authenticated()
                .anyRequest().permitAll()
                .and()
                .requiresChannel()
                .antMatchers("/spitter/form").requiresSecure(); //HTTPS
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //인메모리 사용자 저장소
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER").and()
//                .withUser("admin").password("password").roles("USER", "ADMIN");

        //데이터베이스 테이블
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, true from Spitter where username=?")
//                .authoritiesByUsernameQuery("select username, 'ROLE_USER' from Spitter where username=?")
//                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));

        //LDAP 기반 인증
//        auth.ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0})")
//                .groupSearchFilter("ou=groups")
//                .groupSearchFilter("member={0}")
//                .contextSource()  //원격 SDAP 서버에서 조회
//                .root("dc=habuma,dc=com")
//                .ldif("classpath:users.ldif");
//                .url("ldap://habuma.com:389/dc=habuma,dc=com")
//                .passwordCompare()
//                .passwordEncoder(new Md5PasswordEncoder())
//                .passwordAttribute("passwordcode")

        auth.userDetailsService(new SpitterUserService(spitterRepository));

    }
}
