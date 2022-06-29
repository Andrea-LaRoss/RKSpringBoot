package com.si.rkspringboot.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
import java.net.URISyntaxException;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserConfig Config;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        String errMsg = "";

        if(email == null) {

            errMsg = "Nome utente assente";
            logger.warn(errMsg);

            throw new UsernameNotFoundException(errMsg);
        }

        com.si.rkspringboot.entity.User user = this.GetHttpValue(email);

        if(user == null) {
            errMsg = String.format("Utente non trovato");
            logger.warn(errMsg);
            throw new UsernameNotFoundException(errMsg);
        }

        User.UserBuilder builder = null;
        builder = org.springframework.security.core.userdetails.User.withUsername(user.getEmail());
        builder.disabled(false);
        builder.password(user.getPassword());

        if(user.isAdmin()) {
            String[] profili =  {"USER", "ADMIN"};
            builder.authorities(profili);
        } else {
            String[] profili = {"USER"};
            builder.authorities(profili);
        }

        return builder.build();

    }


    private com.si.rkspringboot.entity.User GetHttpValue(String email) {

        URI url = null;

        try {
            String SrvUrl = Config.getSrvUrl();
            url = new URI(SrvUrl + email);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(Config.getEmail(), Config.getPassword()));

        com.si.rkspringboot.entity.User user = null;

        try {
            user = restTemplate.getForObject(url, com.si.rkspringboot.entity.User.class);
        } catch (Exception e) {

            String Errmsg = String.format("Connessione al servizio non riuscita");
            logger.warn(Errmsg);
        }

        return user;
    }

}
