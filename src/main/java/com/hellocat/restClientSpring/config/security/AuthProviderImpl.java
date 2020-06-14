package com.hellocat.restClientSpring.config.security;

import com.hellocat.restClientSpring.dto.RoleDto;
import com.hellocat.restClientSpring.dto.UserDto;
import com.hellocat.restClientSpring.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private SiteService siteService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthProviderImpl(SiteService siteService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.siteService = siteService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        UserDto user = siteService.findUserByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("UserDto not found");
        }
        String password = authentication.getCredentials().toString();
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }
        Set<RoleDto> authorities = user.getRolesSet();
        return new UsernamePasswordAuthenticationToken(user, null, authorities);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}