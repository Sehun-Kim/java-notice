package com.assignment.rsupport.noticejava.web;

import com.assignment.rsupport.noticejava.domain.notice.Notice;
import com.assignment.rsupport.noticejava.domain.user.User;
import com.assignment.rsupport.noticejava.security.LoginUser;
import com.assignment.rsupport.noticejava.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/notices", "/"})
public class NoticeController {
    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;

    @GetMapping
    public String list(Model model) {
        logger.debug("notices!!");
        model.addAttribute("notices", noticeService.findAll());
        return "index";
    }

    @GetMapping("/form")
    public String form(@LoginUser User loginedUser) {
        logger.debug("Notice Form");
        return "notice/form";
    }

    @PostMapping
    public String create(@LoginUser User loginedUser, Notice notice) {
        noticeService.add(loginedUser, notice);
        return "redirect:/notices";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") long noticeId) {
        model.addAttribute("notice", noticeService.findById(noticeId));
        return "notice/show";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@LoginUser User loginedUser, @PathVariable("id") long noticeId, Model model) {
        model.addAttribute("notice", noticeService.findByIdWithWriter(noticeId, loginedUser));
        return "notice/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@LoginUser User loginedUser, @PathVariable("id") long noticeId, Notice updateNotice) {
        noticeService.update(noticeId, loginedUser, updateNotice);
        return "redirect:/notices/" + noticeId;
    }
}
