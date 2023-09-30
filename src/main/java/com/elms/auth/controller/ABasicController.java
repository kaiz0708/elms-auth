package com.elms.auth.controller;

import com.elms.auth.constant.ElmsConstant;
import com.elms.auth.jwt.LandingIsJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import com.elms.auth.service.impl.UserServiceImpl;

import java.util.Objects;

public class ABasicController {
    @Autowired
    private UserServiceImpl userService;

    public long getCurrentUser(){
        LandingIsJwt landingIsJwt = userService.getAddInfoFromToken();
        return landingIsJwt.getAccountId();
    }

    public long getTokenId(){
        LandingIsJwt landingIsJwt = userService.getAddInfoFromToken();
        return landingIsJwt.getTokenId();
    }

    public LandingIsJwt getSessionFromToken(){
        return userService.getAddInfoFromToken();
    }

    public boolean isSuperAdmin(){
        LandingIsJwt landingIsJwt = userService.getAddInfoFromToken();
        if(landingIsJwt !=null){
            return landingIsJwt.getIsSuperAdmin();
        }
        return false;
    }

    public boolean isShop(){
        LandingIsJwt landingIsJwt = userService.getAddInfoFromToken();
        if(landingIsJwt !=null){
            return Objects.equals(landingIsJwt.getUserKind(), ElmsConstant.USER_KIND_SHOP);
        }
        return false;
    }

    public boolean isTabletApp(){
        LandingIsJwt landingIsJwt = userService.getAddInfoFromToken();
        if(landingIsJwt !=null){
            return Objects.equals(landingIsJwt.getUserKind(), ElmsConstant.USER_KIND_TABLET);
        }
        return false;
    }

    public boolean isBoardApp(){
        LandingIsJwt landingIsJwt = userService.getAddInfoFromToken();
        if(landingIsJwt !=null){
            return Objects.equals(landingIsJwt.getUserKind(), ElmsConstant.USER_KIND_BOARD);
        }
        return false;
    }

    public boolean isPaymentApp(){
        LandingIsJwt landingIsJwt = userService.getAddInfoFromToken();
        if(landingIsJwt !=null){
            return Objects.equals(landingIsJwt.getUserKind(), ElmsConstant.USER_KIND_PAYMENT);
        }
        return false;
    }

    public boolean isMobileEmployeeApp(){
        LandingIsJwt landingIsJwt = userService.getAddInfoFromToken();
        if(landingIsJwt !=null){
            return Objects.equals(landingIsJwt.getUserKind(), ElmsConstant.USER_KIND_MOBILE);
        }
        return false;
    }

    public boolean isWebsiteCustomer(){
        LandingIsJwt landingIsJwt = userService.getAddInfoFromToken();
        if(landingIsJwt !=null){
            return Objects.equals(landingIsJwt.getUserKind(), ElmsConstant.USER_KIND_WEBSITE);
        }
        return false;
    }

    public String getCurrentToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            OAuth2AuthenticationDetails oauthDetails =
                    (OAuth2AuthenticationDetails) authentication.getDetails();
            if (oauthDetails != null) {
                return oauthDetails.getTokenValue();
            }
        }
        return null;
    }
}
