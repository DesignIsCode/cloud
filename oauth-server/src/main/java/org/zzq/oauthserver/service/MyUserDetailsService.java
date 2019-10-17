package org.zzq.oauthserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zzq.entity.Role;
import org.zzq.entity.User;
import org.zzq.oauthserver.mapper.RoleMapper;
import org.zzq.oauthserver.mapper.UserMapper;

import java.util.List;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (null != user){
            List<Role> roles = roleMapper.getRolesByUserId(user.getId());
            user.setAuthorities(roles);
        }else {
            throw new UsernameNotFoundException("User '" + username + "' can not be found");
        }

        // 添加权限
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        for (Role userRole : (List<Role>)user.getAuthorities()) {
//            authorities.add(new SimpleGrantedAuthority(userRole.getName()));
//        }
//        System.out.println(user);
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
//                authorities);
        return user;
    }
}
