package com.assignment.rsupport.noticejava.service;

import com.assignment.rsupport.noticejava.domain.notice.Notice;
import com.assignment.rsupport.noticejava.domain.notice.NoticeRepository;
import com.assignment.rsupport.noticejava.domain.user.User;
import com.assignment.rsupport.noticejava.exception.UnAuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

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
                .orElseThrow(UnAuthorizationException::new);
    }

    public Page<Notice> findAll(Pageable pageable) {
        return noticeRepository.findAll(pageable);
    }

    @Transactional
    public Notice update(long noticeId, User loginedUser, Notice updateNotice) {
        return findByIdWithWriter(noticeId, loginedUser)
                .update(updateNotice);
    }

    public void delete(long noticeId, User loginedUser) {
        noticeRepository.delete(findByIdWithWriter(noticeId, loginedUser));
    }
}
