package com.ohgiraffers.section02.uses;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RequestWrapper extends HttpServletRequestWrapper {

    /* 필기. 부모인 HttpServletRequestWrapper에 기본 생성자가 없으므로 request를 전달해주는 생성자가 필요하다. */
    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String key) {

        /* 설명. 'password'라는 키 값으로 getParameter 사용 시, 그 반환값은 암호화하여 반환. */
        String value = "";

        if("password".equals(key)) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            value = passwordEncoder.encode(super.getParameter(key));
        } else {
            value = super.getParameter(key);
        }

        return value;
    }
}
