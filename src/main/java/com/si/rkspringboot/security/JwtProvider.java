package com.si.rkspringboot.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class JwtProvider {

    private static final Logger log = LogManager.getLogger(JwtProvider.class);

    public static final String issuer = "demo-api-guide";
    public static String secret;
    public static String prefix;
    public static String headerParam;


    @Autowired
    public JwtProvider(Environment env) {
        secret = env.getProperty("security.secret");
        prefix = env.getProperty("security.prefix");
        headerParam = env.getProperty("security.param");
        if(secret == null || prefix == null || headerParam == null) {
            throw new BeanInitializationException("Non è stato possibile assegnare le proprietà di security");
        }
    }


    public static String createJwt(String subject, Map<String, Object> payloadClaims) {

        JWTCreator.Builder builder = JWT.create().withSubject(subject).withIssuer(issuer);
        final DateTime now = DateTime.now();
        builder.withIssuedAt(now.toDate()).withExpiresAt(now.plusDays(1).toDate());

        if (payloadClaims.isEmpty()) {
            log.warn("You are building a JWT without header claims");
        }
        for (Map.Entry<String, Object> entry : payloadClaims.entrySet()) {
            builder.withClaim(entry.getKey(), entry.getValue().toString());
        }
        return builder.sign(Algorithm.HMAC256(JwtProvider.secret));
    }


    public static DecodedJWT verifyJwt(String jwt) {
        return JWT.require(Algorithm.HMAC256(JwtProvider.secret)).withIssuer(JwtProvider.issuer).build().verify(jwt);
    }

}
