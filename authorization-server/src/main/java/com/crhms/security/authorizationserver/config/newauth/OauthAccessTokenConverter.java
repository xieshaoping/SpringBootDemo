package com.crhms.security.authorizationserver.config.newauth;

import com.crhms.security.authorizationserver.security.UserDetailServiceImpl;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

/**
 * @author XieShaoping
 */
public class OauthAccessTokenConverter extends DefaultAccessTokenConverter {

    public OauthAccessTokenConverter(UserDetailServiceImpl userService) {
        DefaultUserAuthenticationConverter converter = new DefaultUserAuthenticationConverter();
        //关键一步，不设置，没有用户详情信息
        converter.setUserDetailsService(userService);
        super.setUserTokenConverter(converter);
    }

}
