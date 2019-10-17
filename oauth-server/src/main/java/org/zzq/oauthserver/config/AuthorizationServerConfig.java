package org.zzq.oauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.zzq.oauthserver.service.MyUserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * 配置 token 节点的安全策略
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()");  // 获取 token 的策略
        security.checkTokenAccess("isAuthenticated()");
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //添加客户端信息
        // 使用in-memory存储客户端信息
//        clients.inMemory()
//                // client_id
//                .withClient("clientId")
//                // client_secret
//                .secret(passwordEncoder.encode("1234"))
//                // 该client允许的授权类型
//                .authorizedGrantTypes("authorization_code","refresh_token")
//                // 允许的授权范围
//                .scopes("all").redirectUris("http://localhost:8080");
        clients.withClientDetails(jdbcClientDetailsService());//设置客户端的配置从数据库中读取，存储在oauth_client_details表
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints
//                // tonken 存储于内存中
//                .tokenStore(new InMemoryTokenStore())
//                .authenticationManager(authenticationManager);
        endpoints.authenticationManager(authenticationManager) // 开启密码验证，来源于 WebSecurityConfigurerAdapter
                .userDetailsService(myUserDetailsService) // 读取验证用户的信息
                .tokenStore(tokenStore());
    }

}
