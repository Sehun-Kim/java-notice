package com.assignment.rsupport.support.util;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

public class HttpSessionUtil {
    public static final String USER_SESSION = "loginedUser";

    public static Optional<Object> getUserFromSession(NativeWebRequest webRequest) {
        return Optional.ofNullable(webRequest.getAttribute(USER_SESSION, WebRequest.SCOPE_SESSION));
    }
}
