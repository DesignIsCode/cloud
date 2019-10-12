package org.zzq.entity;

import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Alias("role")
public class Role implements GrantedAuthority {
    private Long id;
    private String name;

    private List<Permission> permissions;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
