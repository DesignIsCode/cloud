package org.zzq.oauth.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zzq.oauth.entity.User;

@Repository
public interface UserMapper {

    public User loadUserByUsername(@Param("username")String username);
}
