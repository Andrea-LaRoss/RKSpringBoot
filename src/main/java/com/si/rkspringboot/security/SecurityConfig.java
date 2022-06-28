package com.si.rkspringboot.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String REALM = "REAME";

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


   /* @Bean
    @Override
    public UserDetailsService userDetailsService() {

        User.UserBuilder users = User.builder();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(users.username("Andrea")
                .password(new BCryptPasswordEncoder().encode("1234"))
                .roles("USER")
                .build());

        manager.createUser(users.username("AdminTest")
                .password(new BCryptPasswordEncoder().encode("1234"))
                .roles("USER", "ADMIN")
                .build());

        return manager;

    }*/


    private static final String[] USER_MATCHER = {"/api/reservation/**"};
    private static final String[] ADMIN_MATCHER = {"/api/**"};


   @Override
   protected  void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(USER_MATCHER).hasAnyRole("USER")
                .antMatchers(ADMIN_MATCHER).hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       auth
               .userDetailsService(userDetailsService)
               .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Bean
    public AuthEntryPoint getBasicAuthEntryPoint() {
       return new AuthEntryPoint();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

}
