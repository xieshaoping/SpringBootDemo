package com.crhms.security.client2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class  UiSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.oauth2.client.logoutUri}")
    private String logoutUri;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //http.authorizeRequests()方法有很多子方法，每个子匹配器将会按照声明的顺序起作用
                .authorizeRequests()
                .antMatchers("/", "/login**").permitAll()
                .anyRequest().authenticated();

        //退出操作，自身推出后，再退出认证中心的
        http.logout().logoutSuccessUrl("http://localhost:9002/ssoLogout")
                .clearAuthentication(true).deleteCookies(cookieName);

        //跨站保护需要关闭，否则http://localhost:9002/ssoLogout无法访问
        http.csrf().disable();

    }

    @Value("${server.servlet.session.cookie.name}")
    private String cookieName;

}
