package org.zzq.entity;

import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;

@Alias("role")
public class Role implements GrantedAuthority {
    private Long id;
    private String name;

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

    @Override
    public String getAuthority() {
        return name;
    }
}
