package com.si.rkspringboot.security;

import lombok.extern.java.Log;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log
public class AuthEntryPoint extends BasicAuthenticationEntryPoint {

    private static String REALM = "REAME";

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response,
                         final AuthenticationException authException) throws IOException {

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName()
        + "");

        PrintWriter writer = response.getWriter();
        writer.println("Email o password non corrette");

    }


    @Override
    public void afterPropertiesSet() {
        setRealmName(REALM);
        super.afterPropertiesSet();
    }

}
