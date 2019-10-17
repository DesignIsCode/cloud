package org.zzq.oauth.password;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String s = bCryptPasswordEncoder.encode("1234");
        System.out.println(s);
        boolean b = bCryptPasswordEncoder.matches("admin",s);
        System.out.println(b);
    }
}
