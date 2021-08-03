package com.crhms.security.authorizationserver.config.newauth;

import com.crhms.security.authorizationserver.security.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author XieShaoping
 */
@Configuration
public class TokenConfig {

    @Bean
    public TokenStore jwtTokenStore(JwtAccessTokenConverter converter) {
        return new JwtTokenStore(converter);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(UserDetailServiceImpl userService) {
        JwtAccessTokenConverter accessTokenConverter = new OauthJwtAccessTokenConverter(userService);
        accessTokenConverter.setSigningKey("123");
        return accessTokenConverter;
    }

}