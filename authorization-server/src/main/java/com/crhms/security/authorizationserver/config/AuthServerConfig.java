package com.crhms.security.authorizationserver.config;

import com.crhms.security.authorizationserver.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


/**
 * @author XieShaoping
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter tokenConverter;

    @Autowired
    public UserDetailServiceImpl userDetailService;

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .allowFormAuthenticationForClients().checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception { // @formatter:off
        clients.inMemory()
                //Client
                .withClient("sampleClientId")
                .secret(passwordEncoder().encode("secret"))
                .authorizedGrantTypes("implicit", "authorization_code", "client_credentials")
                .scopes("read", "write", "foo", "bar", "webclient", "all")
                .autoApprove(true)
                .accessTokenValiditySeconds(3600)
                .redirectUris("http://localhost:9001/login")

                .and()
                //Client2
                .withClient("sampleClientId2")
                .secret(passwordEncoder().encode("secret"))
                .authorizedGrantTypes("implicit", "authorization_code", "client_credentials")
                .scopes("read", "write", "foo", "bar", "webclient")
                .autoApprove(true)
                .accessTokenValiditySeconds(3600)
                .redirectUris("http://localhost:9000/login")
                .and()

                //resource-server
                .withClient("resourceserver")
                .secret(passwordEncoder().encode("secret"))
                .authorizedGrantTypes("implicit", "authorization_code", "client_credentials")
                .scopes("read", "write", "foo", "bar", "webclient")
                .autoApprove(true)
                .accessTokenValiditySeconds(3600)
                .redirectUris("http://localhost:9000/login");
    } // @formatter:on

//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        defaultTokenServices.setSupportRefreshToken(true);
//        return defaultTokenServices;
//    }

//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(accessTokenConverter()));
//
//        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
//        defaultAccessTokenConverter.setUserTokenConverter(new MyUserAuthenticationConverter(userDetailService));
//
//        endpoints
//                .tokenStore(tokenStore())
//                .tokenEnhancer(tokenEnhancerChain)
//                .authenticationManager(authenticationManager)
//                .accessTokenConverter(defaultAccessTokenConverter)
//                .userDetailsService(userDetailService);
//    }

//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }

//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//         JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
//
//        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
//        userAuthenticationConverter.setUserDetailsService(userDetailService);
//
//        defaultAccessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
//
//        converter.setAccessTokenConverter(defaultAccessTokenConverter);
//        converter.setSigningKey("123");
//        return converter;
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(tokenConverter);
    }

}
