package com.hellocat.restClientSpring.controller;

import com.hellocat.restClientSpring.dto.UserDto;
import com.hellocat.restClientSpring.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private SiteService siteService;

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("userForm", new UserDto());
        return "signUp";
    }

    @PostMapping("/registration")
    public String userRegistration(@ModelAttribute("userForm") UserDto user,
                                   Model model) {
        boolean rezult = false;
        try {
            rezult = siteService.saveUser(user);

        } catch (Exception e) {

        }
        if (rezult) {
            model.addAttribute("user", user);
            return "redirect:/user";
        } else {
            model.addAttribute("userForm", new UserDto());
            model.addAttribute("error", "Registration failed");
            return "signUp";
        }
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(name = "error", required = false) Boolean error,
                               Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", "invalid username or password");
        }
        return "signIn";
    }

}
