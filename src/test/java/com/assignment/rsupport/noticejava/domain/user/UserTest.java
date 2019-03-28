package com.assignment.rsupport.noticejava.domain.user;

import com.assignment.rsupport.support.test.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest extends BaseTest {

    @Test
    public void matchPassword() {
        softly.assertThat(TEST_USER.matchPassword("1234")).isTrue();
    }

    @Test
    public void matchPassword_fail() {
        softly.assertThat(TEST_USER.matchPassword("414")).isFalse();
    }
}