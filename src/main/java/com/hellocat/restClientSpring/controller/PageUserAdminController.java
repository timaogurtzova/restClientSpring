package com.hellocat.restClientSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class PageUserAdminController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        return "user/userpage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        return "admin/adminPage";
    }

}
