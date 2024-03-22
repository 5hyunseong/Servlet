package com.ohgiraffers.section02.uses;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/member/*")     // 필기. member 서비스인 경우에만 암호화 처리할 수 있도록 한다.
public class PasswordEncryptFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		System.out.println("패스워드 암호화 필터의 doFilter() 실행됨.");

		/* 설명. 기존 request 객체를 우리가 재정의한 request 객체로 교체. */
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        RequestWrapper wrapper = new RequestWrapper(httpRequest);

        filterChain.doFilter(wrapper, servletResponse);
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}
}
