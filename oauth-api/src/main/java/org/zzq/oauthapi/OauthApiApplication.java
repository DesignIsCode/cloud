package org.zzq.oauthapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@MapperScan("org.zzq.oauthapi.mapper")
public class OauthApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthApiApplication.class, args);
    }


}
