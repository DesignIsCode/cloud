package org.zzq.entity;

import org.apache.ibatis.type.Alias;

import java.util.Arrays;
import java.util.List;

@Alias("permission")
public class Permission {
    private Long id;
    private String url;
    private String name;
    private String description;
    private String permission;
    private List<String> permissions;
    private Long pid;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getPermissions() {
        return Arrays.asList(this.permission.trim().split(","));
    }
}
