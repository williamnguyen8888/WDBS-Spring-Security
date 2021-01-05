package com.yuen.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String a = phathienDuongDan(authentication);
        if(response.isCommitted()){
            return;
        }
        redirectStrategy.sendRedirect(request, response, a);
    }

    protected String phathienDuongDan(Authentication authentication){
        String url = "";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles= new ArrayList<>();
        for (GrantedAuthority a: authorities
             ) {
            roles.add(a.getAuthority());
        }
        if(roles.contains("ROLE_ADMIN")){
            url = "/admin";
        }
        else if(roles.contains("ROLE_MEMBER")){
            url="/";
        }
        else {
            url="/khongcoquyen";
        }
        return url;
    }
}
