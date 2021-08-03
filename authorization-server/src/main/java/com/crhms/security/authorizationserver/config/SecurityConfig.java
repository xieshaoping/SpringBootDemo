package com.crhms.security.authorizationserver.config;

import com.crhms.security.authorizationserver.config.newauth.MyFilterSecurityInterceptor;
import com.crhms.security.authorizationserver.security.AuthenticationProcessingFilter;
import com.crhms.security.authorizationserver.security.UserDetailServiceImpl;
import com.crhms.security.authorizationserver.security.UserPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author XieShaoping
 */
@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserDetailServiceImpl userDetailService;

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        //AccessDecisionManager
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // @formatter:off
        http.requestMatchers()
                .antMatchers("/login", "/oauth/authorize", "/ssoLogout")
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                //任何请求通过登录认证
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and()

                .csrf().disable();
        //.addFilterAt(authenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);

       // http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);

    }

    public AuthenticationProcessingFilter authenticationProcessingFilter() throws Exception {
        AuthenticationProcessingFilter filter = new AuthenticationProcessingFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // @formatter:off
        //auth.parentAuthenticationManager()
        auth.userDetailsService(userDetailService).passwordEncoder(new UserPasswordEncoder());
    }

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**",
                "/js/**",
                "/image/**",
                "/favicon.ico");
    }
}
