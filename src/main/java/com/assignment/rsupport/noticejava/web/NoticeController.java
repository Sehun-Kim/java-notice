package com.assignment.rsupport.noticejava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/notices", "/"})
public class NoticeController {
    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
    @GetMapping
    public String list() {
        logger.debug("notices!!");
        return "index";
    }
}
