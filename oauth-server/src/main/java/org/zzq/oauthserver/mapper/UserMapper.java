package org.zzq.oauthserver.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zzq.entity.User;

@Repository
public interface UserMapper {

    User loadUserByUsername(@Param("username") String username);

    int createUser(User user);
}
