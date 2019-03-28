package com.assignment.rsupport.noticejava.web;

import com.assignment.rsupport.noticejava.domain.user.User;
import com.assignment.rsupport.noticejava.exception.UnAuthenticationException;
import com.assignment.rsupport.noticejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String form() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        try {
            User loginedUser = userService.login(userId, password);
            return "redirect:/notices";
        } catch (UnAuthenticationException e) {
            return "redirect:/login";
        }
    }
}
