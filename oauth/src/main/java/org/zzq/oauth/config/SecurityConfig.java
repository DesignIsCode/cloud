package org.zzq.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.zzq.oauth.service.MyUserDetailsService;

import javax.servlet.http.HttpServletRequest;

@Configuration
//开启spring security服务
@EnableWebSecurity
//开启security全局注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    //配置如何通过拦截器保护请求
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.requestMatchers()
                .antMatchers("/oauth/**","/login","/login-error","/**")
                .and()
                .authorizeRequests()
                .antMatchers("/getVerifyCode").permitAll()
                .antMatchers("/oauth/**","/home").authenticated()
                .and()
                .formLogin().loginPage( "/login" ).failureUrl( "/login-error" ).permitAll()
                .authenticationDetailsSource(authenticationDetailsSource)
                .and()
                .httpBasic()
                .and()
                .rememberMe()
                .and()
                .logout();
    }

    //配置user-detail服务
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //基于内存来存储用户信息
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("user").password(new BCryptPasswordEncoder().encode("user")).roles("USER").and()
//                .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("USER","ADMIN");

//        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        auth.authenticationProvider(customAuthenticationProvider);
    }

    //配置spring security的filter链
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    /**
     * 注入自定义PermissionEvaluator
     */
    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return handler;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
