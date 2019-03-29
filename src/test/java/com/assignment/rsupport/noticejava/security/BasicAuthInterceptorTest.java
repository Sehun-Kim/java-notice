package com.assignment.rsupport.noticejava.security;

import com.assignment.rsupport.noticejava.exception.UnAuthenticationException;
import com.assignment.rsupport.noticejava.service.UserService;
import com.assignment.rsupport.support.test.BaseTest;
import com.assignment.rsupport.support.util.HttpSessionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Base64;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasicAuthInterceptorTest extends BaseTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private BasicAuthInterceptor basicAuthInterceptor;

    @Test
    public void preHandle_fail() throws Exception {
        String userId = TEST_USER_1.getUserId();
        String password = TEST_USER_1.getPassword();
        MockHttpServletRequest mockRequest = generateBasicAuthHttpRequest(userId, password);

        when(userService.login(userId, password)).thenThrow(UnAuthenticationException.class);

        basicAuthInterceptor.preHandle(mockRequest, null, null);
        softly.assertThat(mockRequest.getSession().getAttribute(HttpSessionUtil.USER_SESSION)).isNull();
    }

    @Test
    public void preHandle_success() throws Exception {
        String userId = TEST_USER_1.getUserId();
        String password = TEST_USER_1.getPassword();
        MockHttpServletRequest mockRequest = generateBasicAuthHttpRequest(userId, password);

        when(userService.login(userId, password)).thenReturn(TEST_USER_1);

        basicAuthInterceptor.preHandle(mockRequest, null, null);
        softly.assertThat(mockRequest.getSession().getAttribute(HttpSessionUtil.USER_SESSION)).isEqualTo(TEST_USER_1);
    }

    // basic auth를 요청하는 가짜 request 생성
    private MockHttpServletRequest generateBasicAuthHttpRequest(String userId, String password) {
        String encodedBasicAuth = Base64.getEncoder().encodeToString(String.format("%s:%s", userId, password).getBytes());
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Basic " + encodedBasicAuth);
        return request;
    }
}