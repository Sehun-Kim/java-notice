package com.assignment.rsupport.noticejava.security;

import com.assignment.rsupport.noticejava.domain.user.User;
import com.assignment.rsupport.noticejava.exception.UnAuthenticationException;
import com.assignment.rsupport.support.test.BaseTest;
import com.assignment.rsupport.support.util.HttpSessionUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginUserHandlerMethodArgumentResolverTest extends BaseTest {
    @Mock
    private MethodParameter parameter;

    @Mock
    private NativeWebRequest request;

    @Mock
    private LoginUser annotedLoginUser;

    private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;

    @Before
    public void setUp() throws Exception {
        loginUserHandlerMethodArgumentResolver = new LoginUserHandlerMethodArgumentResolver();
    }

    @Test
    public void supportParameter_true() {
        when(parameter.hasParameterAnnotation(LoginUser.class)).thenReturn(true);
        softly.assertThat(loginUserHandlerMethodArgumentResolver.supportsParameter(parameter)).isTrue();
    }

    @Test
    public void supportParameter_false() {
        when(parameter.hasParameterAnnotation(LoginUser.class)).thenReturn(false);
        softly.assertThat(loginUserHandlerMethodArgumentResolver.supportsParameter(parameter)).isFalse();
    }

    @Test
    public void loginUser_success() throws Exception {
        when(request.getAttribute(HttpSessionUtil.USER_SESSION, WebRequest.SCOPE_SESSION)).thenReturn(TEST_USER_1);
        User loginUser = (User) loginUserHandlerMethodArgumentResolver.resolveArgument(parameter, null, request, null);

        softly.assertThat(loginUser).isEqualTo(TEST_USER_1);
    }

    @Test(expected = UnAuthenticationException.class)
    public void loginUser_empty() throws Exception {
        when(request.getAttribute(HttpSessionUtil.USER_SESSION, WebRequest.SCOPE_SESSION)).thenReturn(null);
        loginUserHandlerMethodArgumentResolver.resolveArgument(parameter, null, request, null);
    }
}