package com.assignment.rsupport.noticejava.web;

import com.assignment.rsupport.noticejava.domain.user.User;
import com.assignment.rsupport.noticejava.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/form")
    public String createForm() {
        return "user/form";
    }

    @PostMapping
    public String create(User user) {
        userService.add(user);
        return "redirect:/login";
    }
}
