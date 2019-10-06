package org.zzq.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //http.csrf().disable();
        http.requestMatchers()
                .antMatchers("/oauth/**","/")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**","/**").authenticated()
                .and()
                .formLogin().loginPage( "/login" ).failureUrl( "/login-error" );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //基于内存来存储用户信息
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user").password(new BCryptPasswordEncoder().encode("user")).roles("USER").and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("USER","ADMIN");
    }


}
