package com.ohgiraffers.section02.uses;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/* 설명. 해당 필터는 기존의 어노테이션 방식 대신 XML 방식으로 필터를 구성할 것이다. */
public class EncodingFilter implements Filter {

    private String encodingType;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("XML 파일에서 인코딩 설정 필터의 doFilter() 실행됨.");
        /* 필기. xml에 설정한 init-param의 key를 이용하여 fConfig에서 값을 꺼내올 수 있다. */
        encodingType = filterConfig.getInitParameter("encoding-type");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        System.out.println("인코딩 설정 필터의 doFilter() 실행됨.");

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        /* Note. Tomcat v10 이상일 경우 기본 인코딩 설정이 UTF-8이라 사실상 아래 작업이 불필요함. */
        /* 설명. 요청이 POST 요청일 때는 인코딩 설정을 UTF-8로 사전에 처리해주고 이후 필터나 서블릿으로 넘긴다. */
        if("POST".equals(httpRequest.getMethod())) {
            servletRequest.setCharacterEncoding("UTF-8");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
