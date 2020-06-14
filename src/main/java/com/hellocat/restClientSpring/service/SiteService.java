package com.hellocat.restClientSpring.service;

import com.hellocat.restClientSpring.dto.UserDto;

import java.util.List;

public interface SiteService {

    List<UserDto> findAllUsers();

    UserDto findUserById(Long id);

    UserDto findUserByName(String name);

    boolean saveUser(UserDto user);

    void deleteUser(Long id);

    void updateUser(UserDto user);

}
