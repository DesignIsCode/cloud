package org.zzq.oauth.entity;

import org.springframework.security.core.GrantedAuthority;

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
