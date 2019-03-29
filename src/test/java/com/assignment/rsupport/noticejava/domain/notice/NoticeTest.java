package com.assignment.rsupport.noticejava.domain.notice;

import com.assignment.rsupport.support.test.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoticeTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(NoticeTest.class);

    @Test
    public void matchWriter() {
        Notice notice = TEST_NOTICE_1;
        softly.assertThat(notice.isWriter(TEST_USER_1)).isTrue();
    }

    @Test
    public void update() {
        Notice notice = new Notice("test", "test!!!!!", TEST_USER_1);
        logger.debug("original : {}", notice);
        notice.update(TEST_NOTICE_1);
        logger.debug("update : {}", notice);
        softly.assertThat(notice.getContent().equals(TEST_NOTICE_1.getContent())).isTrue();
    }
}