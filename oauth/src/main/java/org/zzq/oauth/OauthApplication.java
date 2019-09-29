package org.zzq.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zzq.entity.User;

@SpringBootApplication
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
        User user = new User();
        user.setId(1L);
        System.out.println(user);
        System.out.println("-----start-------");
    }

}
