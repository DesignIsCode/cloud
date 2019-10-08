package org.zzq.oauth.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zzq.oauth.entity.Role;

import java.util.List;

@Repository
public interface RoleMapper {

    List<Role> getRolesByUserId(@Param("userId") Long id);
}
