package com.hellocat.restClientSpring.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;
import java.util.Set;

public class RoleDto implements GrantedAuthority {

    private Long id;

    private RoleType roleType;

    private Set<UserDto> users;

    public RoleDto(Long id, RoleType roleType) {
        this.id = id;
        this.roleType = roleType;
    }

    public RoleDto(RoleType roleType) {
        this.roleType = roleType;
    }

    public RoleDto(String role) {
        if (role.contains("ADMIN")) {
            this.id = 1L;
            this.roleType = RoleType.ROLE_ADMIN;
        } else {
            this.id = 2L;
            this.roleType = RoleType.ROLE_USER;
        }
    }

    protected RoleDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        if (id == 1L) {
            this.roleType = RoleType.ROLE_ADMIN;
        } else {
            this.roleType = RoleType.ROLE_USER;
        }
    }

    @Override
    public String getAuthority() {
        return roleType.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto role = (RoleDto) o;
        return roleType == role.roleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleType);
    }

    @Override
    public String toString() {
        return roleType.toString();
    }
}
