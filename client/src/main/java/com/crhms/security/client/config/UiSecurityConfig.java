package com.crhms.security.client.config;

import com.crhms.security.client.auth.MyFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)//如果要做本地权限控制，必须加这条注解
public class UiSecurityConfig extends WebSecurityConfigurerAdapter {

    // @Autowired
    // @Qualifier("ssoLogoutfilterRegistrationBean")
    // private FilterRegistrationBean ssoLogoutfilterRegistrationBean;

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http

                .antMatcher("/**")
                .authorizeRequests()

                .antMatchers("/", "/login**")
                .permitAll()

                .anyRequest().authenticated()

                .and()
                //.addFilterBefore(oauth2ClientAuthenticationProcessingFilter,BasicAuthenticationFilter.class)
                .csrf().disable()
                //.addFilterBefore(ssoLogoutfilterRegistrationBean.getFilter(), LogoutFilter.class)
        ;

        //退出操作，自身推出后，再退出认证中心的
        http.logout().logoutSuccessUrl("http://localhost:9002/ssoLogout")
                .clearAuthentication(true).deleteCookies("JSESSIONID_CLIENT2");

        //跨站保护需要关闭，否则http://localhost:9002/ssoLogout无法访问
        http.csrf().disable();

        //http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);

    }

    @Value("${server.servlet.session.cookie.name}")
    private String cookieName;

}
