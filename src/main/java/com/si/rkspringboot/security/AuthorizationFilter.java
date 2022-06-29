package com.si.rkspringboot.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.si.rkspringboot.entity.User;
import com.si.rkspringboot.repository.UserRepository;
import jdk.nashorn.internal.ir.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    @Autowired
    private final UserRepository userRepository;

    private final ObjectMapper mapper;

    private JwtProvider provider;


    public AuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.mapper = new ObjectMapper();
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String header = request.getHeader(JwtProvider.headerParam);
        if (header != null && header.startsWith(JwtProvider.prefix)) {
            Set<String> roles = new HashSet<>();
            roles.add("USER");
            final DecodedJWT decoded = JwtProvider.verifyJwt(header.replace(JwtProvider.prefix, ""));
            final ObjectNode userNode = this.mapper.readValue(decoded.getClaim("user").asString(), ObjectNode.class);
            final User user = this.mapper.convertValue(userNode, User.class);
            if(user.isAdmin()) {
                roles.add("ADMIN");
            }
            this.userRepository.findById(user.getId()).ifPresent(entity -> {
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, roles.stream().map(UserRole::name).map(SimpleGrantedAuthority::new).collect(Collectors.toSet())));
            });
        }
        chain.doFilter(request, response);
    }

}
