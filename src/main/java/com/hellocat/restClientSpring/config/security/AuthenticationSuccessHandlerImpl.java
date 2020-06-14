package com.hellocat.restClientSpring.config.security;

import com.hellocat.restClientSpring.dto.RoleDto;
import com.hellocat.restClientSpring.dto.RoleType;
import com.hellocat.restClientSpring.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        HttpSession session = request.getSession();
        UserDto user = (UserDto) authentication.getPrincipal();

        session.setAttribute("authorities", authentication.getAuthorities());
        session.setAttribute("user", user);

        response.setStatus(HttpServletResponse.SC_OK);

        if (user.getRolesSet().contains(new RoleDto(RoleType.ROLE_ADMIN))) {
            response.sendRedirect("/admin");
        } else if (user.getRolesSet().contains(new RoleDto(RoleType.ROLE_USER))) {
            response.sendRedirect("/user");
        }
    }
}