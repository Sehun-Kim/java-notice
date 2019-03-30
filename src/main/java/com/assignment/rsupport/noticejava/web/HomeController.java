package com.assignment.rsupport.noticejava.web;

import com.assignment.rsupport.noticejava.domain.notice.Notice;
import com.assignment.rsupport.noticejava.service.NoticeService;
import com.assignment.rsupport.support.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/")
    public String list(Model model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = PageUtil.PAGE_SIZE) Pageable pageable) {
        Page<Notice> page = noticeService.findAll(pageable);
        PageUtil pageUtil = new PageUtil(pageable.getPageNumber(), page.getTotalPages() - 1);

        logger.debug("pageUtil : {} , before : {}, next : {}", pageUtil, pageUtil.getBeforeFirstPageNum(), pageUtil.getNextFirstPageNum());
        model.addAttribute("notices", page);
        model.addAttribute("pageUtil", pageUtil);
        return "index";
    }
}
