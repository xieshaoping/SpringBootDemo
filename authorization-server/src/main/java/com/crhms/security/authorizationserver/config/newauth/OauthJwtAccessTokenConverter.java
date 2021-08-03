package com.crhms.security.authorizationserver.config.newauth;

import com.crhms.security.authorizationserver.security.UserDetailServiceImpl;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author XieShaoping
 */
public class OauthJwtAccessTokenConverter extends JwtAccessTokenConverter {

    public OauthJwtAccessTokenConverter(UserDetailServiceImpl userService) {
        super.setAccessTokenConverter(new OauthAccessTokenConverter(userService));
    }

}
