package com.si.rkspringboot.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.si.rkspringboot.entity.User;
import com.si.rkspringboot.repository.UserRepository;
import com.si.rkspringboot.service.CustomUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthorizationFilter extends BasicAuthenticationFilter {


    private final UserRepository userRepository;

    private CustomUserDetailsService customUserDetailsService;


    public AuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.customUserDetailsService = new CustomUserDetailsService(this.userRepository);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JwtProvider.headerParam);
        if (header != null && header.startsWith(JwtProvider.prefix)) {
            header = header.replace("Bearer ", "");
            DecodedJWT decoded = JwtProvider.verifyJwt(header);
            User user = this.userRepository.searchUserByEmail(decoded.getClaim("sub").asString());
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(decoded.getClaim("sub").asString());
            this.userRepository.findById(user.getId()).ifPresent(entity -> {
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
            });
        }
        chain.doFilter(request, response);
    }

}
