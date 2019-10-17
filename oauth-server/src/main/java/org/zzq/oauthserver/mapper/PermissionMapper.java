package org.zzq.oauthserver.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zzq.entity.Permission;

import java.util.List;

@Repository
public interface PermissionMapper {
    List<Permission> listByRoleId(@Param("roleId") Long roleId);

}
