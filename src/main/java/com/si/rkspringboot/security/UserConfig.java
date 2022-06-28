package com.si.rkspringboot.security;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserConfig {

    private String srvUrl;
    private String email;
    private String password;
}
