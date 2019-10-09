package org.zzq.oauth.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zzq.entity.Role;
import org.zzq.entity.User;

import java.util.List;

@Repository
public interface RoleMapper {

    List<Role> getRolesByUserId(@Param("userId") Long id);

    Role selectByName(@Param("roleName") String roleName);
}
