package com.FlowerShop.FlowerShop.security.config;

import com.FlowerShop.FlowerShop.appuser.AppUser;
import com.FlowerShop.FlowerShop.appuser.AppUserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
 
        AppUser userDetails = (AppUser) authentication.getPrincipal();

        String redirectURL = request.getContextPath();
        AppUserRole appUserRole = userDetails.getAppUserRole();
        switch (appUserRole){
            case USER:
                redirectURL = "";
                break;
            case ADMIN:
                redirectURL = "/admin";
                break;

        }

        response.sendRedirect(redirectURL);

    }
 
}