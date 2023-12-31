package com.elms.auth.config;

import com.elms.auth.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class CustomTokenGranter extends AbstractTokenGranter {

    private UserServiceImpl userService;
    private AuthenticationManager authenticationManager;

    protected CustomTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
    }

    @Autowired
    public CustomTokenGranter(AuthenticationManager authenticationManager,AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType, UserServiceImpl userService) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        return super.getOAuth2Authentication(client, tokenRequest);
    }

    protected OAuth2AccessToken getAccessToken(ClientDetails client, TokenRequest tokenRequest) {
        String tenant = tokenRequest.getRequestParameters().get("tenant");
        String username = tokenRequest.getRequestParameters().get("username");
        String password = tokenRequest.getRequestParameters().get("password");

        try {
            if (SecurityConstant.GRANT_TYPE_ANONYMOUS.equalsIgnoreCase(tokenRequest.getGrantType())){
                return userService.getAccessTokenForAnonymous(client, tokenRequest, "anonymous", tenant, this.getTokenServices());
            } else {
                return userService.getAccessTokenForMultipleTenancies(client, tokenRequest, username, password, tenant, this.getTokenServices());
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new InvalidTokenException("account or tenant invalid");
        } catch (IOException e) {
            e.printStackTrace();
            throw new InvalidTokenException("account or tenant invalid");
        }
    }

}
