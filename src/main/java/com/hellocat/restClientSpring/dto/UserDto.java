package com.hellocat.restClientSpring.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDto implements UserDetails {

    private Long id;

    private Set<RoleDto> roles = new HashSet<>();

    private String name;

    private int age;

    private String password;

    private String city;

    public UserDto() {
        roles = Collections.singleton(new RoleDto(2L, RoleType.ROLE_USER));
    }

    public UserDto(String name, int age, String password, String city, Set<RoleDto> roles) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.city = city;
        this.roles = roles;
    }

    public UserDto(Long id, String name, int age, String password, String city, Set<RoleDto> roles) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
        this.city = city;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<RoleDto> getRolesSet() {
        return roles;
    }

    public String getRoles() {
        String x = roles.toString().replaceAll("\\p{P}", "");
        return x.replaceAll("ROLE", "");
    }

    public void setRolesSet(Set<RoleDto> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto user = (UserDto) o;
        return name.equals(user.name) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}