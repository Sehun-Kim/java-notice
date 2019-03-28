package com.assignment.rsupport.noticejava.security;

import com.assignment.rsupport.noticejava.exception.UnAuthenticationException;
import com.assignment.rsupport.support.util.HttpSessionUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

// 사용자 요청이 Controller에 도달하기 전에 그 요청의 파라미터들을 수정
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return HttpSessionUtil.getUserFromSession(webRequest).orElseThrow(UnAuthenticationException::new);
    }
}
