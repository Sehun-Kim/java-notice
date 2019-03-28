package com.assignment.rsupport.noticejava.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 매개변수로 선언할 때
@Retention(RetentionPolicy.RUNTIME) // 컴파일 이후 Runtime 시점에도 어노테이션을 사용할 수 있음
public @interface LoginUser {
    boolean required() default true;
}
