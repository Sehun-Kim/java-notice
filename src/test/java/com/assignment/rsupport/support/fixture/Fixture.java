package com.assignment.rsupport.support.fixture;

import com.assignment.rsupport.noticejava.domain.notice.Notice;
import com.assignment.rsupport.noticejava.domain.user.User;

public class Fixture {
    protected static User TEST_USER_1 = new User(1L,"tester", "sehun", "1234");
    protected static User TEST_USER_2 = new User(2L,"tester2", "dom", "1234");
    protected static Notice TEST_NOTICE_1 = new Notice("테스트입니다.", "이것은 테스트입니다. 열심히 작성하지 않은 공지지요.", TEST_USER_1);
}
