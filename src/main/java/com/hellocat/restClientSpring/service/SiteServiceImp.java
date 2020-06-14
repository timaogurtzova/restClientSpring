package com.hellocat.restClientSpring.service;

import com.hellocat.restClientSpring.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class SiteServiceImp implements SiteService {

    private final RestTemplate restTemplate;

    private final String serverUrl;

    @Autowired
    public SiteServiceImp(RestTemplate restTemplate, @Value("${application.server.url}") String serverUrl) {
        this.restTemplate = restTemplate;
        this.serverUrl = serverUrl;
    }

    @Override
    public List<UserDto> findAllUsers() {
        return restTemplate.exchange(
                serverUrl + "/getAllUsers",
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<List<UserDto>>() {
                }
        ).getBody();
    }

    @Override
    public UserDto findUserByName(String name) {
        return restTemplate.exchange(
                URI.create(serverUrl + "/getUserWithName"),
                HttpMethod.POST,
                new HttpEntity<>(name),
                new ParameterizedTypeReference<UserDto>() {
                }
        ).getBody();
    }

    @Override
    public UserDto findUserById(Long id) {
        return restTemplate.exchange(
                serverUrl + "/getUser/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UserDto>() {
                }
        ).getBody();
    }

    @Override
    public boolean saveUser(UserDto user) {
        ResponseEntity status = restTemplate.exchange(
                serverUrl + "/createUser/",
                HttpMethod.POST,
                new HttpEntity<>(user),
                new ParameterizedTypeReference<ResponseEntity>() {
                }
        );
        if (status.getStatusCode() == HttpStatus.OK) {
            return true;
        }
        return false;
    }

    @Override
    public void updateUser(UserDto user) {
        restTemplate.postForObject(serverUrl + "/updateUser",
                user,
                ResponseEntity.class);
    }

    @Override
    public void deleteUser(Long id) {
        restTemplate.exchange(
                serverUrl + "/deleteUser/" + id,
                HttpMethod.DELETE,
                null,
                ResponseEntity.class);
    }

}
