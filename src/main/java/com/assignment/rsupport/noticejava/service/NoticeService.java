package com.assignment.rsupport.noticejava.service;

import com.assignment.rsupport.noticejava.domain.notice.Notice;
import com.assignment.rsupport.noticejava.domain.notice.NoticeRepository;
import com.assignment.rsupport.noticejava.domain.user.User;
import com.assignment.rsupport.noticejava.exception.UnAuthorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class NoticeService {
    private static final Logger logger = LoggerFactory.getLogger(NoticeService.class);
    
    @Autowired
    private NoticeRepository noticeRepository;

    public Notice add(User loginedUser, Notice notice) {
        notice.writeBy(loginedUser);
        return noticeRepository.save(notice);
    }

    public Notice findById(long noticeId) {
        return noticeRepository.findById(noticeId).orElseThrow(EntityNotFoundException::new);
    }

    public Notice findByIdWithWriter(long noticeId, User loginedUser) {
        return noticeRepository.findById(noticeId)
                .filter(notice -> notice.isWriter(loginedUser))
                .orElseThrow(UnAuthorization::new);
    }

    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }

    @Transactional // 사실 안 붙혀도 상관 없다.
    public Notice update(long noticeId, User loginedUser, Notice updateNotice) {
        Notice original = findByIdWithWriter(noticeId, loginedUser);
        original.update(updateNotice);
        return noticeRepository.save(original);
    }
}
