package com.assignment.rsupport.noticejava.security;

// 실제 production상에서는 Form Base 방식으로 로그인한다.
// 하지만 테스트를 할 때는 Form 방식을 사용하기 힘드므로 Basic 인증 방식으로 로그인한다.

import com.assignment.rsupport.noticejava.domain.user.User;
import com.assignment.rsupport.noticejava.exception.UnAuthenticationException;
import com.assignment.rsupport.noticejava.service.UserService;
import com.assignment.rsupport.support.util.HttpSessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Base64;

public class BasicAuthInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(BasicAuthInterceptor.class);

    public BasicAuthInterceptor() {
        logger.debug("init BasicAuthInterceptor");
    }

    @Autowired
    private UserService userService;

    /*
     * Controller 실행 요청 전에 수행되는 메서드
     * 클라이언트의 요청을 컨트롤러에 전달 하기 전에 호출
     * return 값으로 boolean 값을 전달하는데 false 인 경우 controller를 실행 시키지 않고 요청 종료
     * 보통 이곳에서 체크작업을 진행한다. (구현자의 취향대로 적용해도 된다)
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        logger.debug("authorization : {}", authorization);

        if (authorization == null || !authorization.startsWith("Basic")) return true; // session에 로그인하지 않고 종료

        String base64Credentials = authorization.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
        final String[] values = credentials.split(":", 2);
        logger.debug("userId : {}", values[0]);
        logger.debug("password : {}", values[1]);

        try {
            User user = userService.login(values[0], values[1]);
            logger.debug("login Success : {}", user);
            request.getSession().setAttribute(HttpSessionUtil.USER_SESSION, user);
            return true;
        } catch (UnAuthenticationException e) {
            return true;
        }
    }
}

