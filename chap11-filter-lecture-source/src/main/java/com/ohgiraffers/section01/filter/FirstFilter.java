package com.ohgiraffers.section01.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

/* 설명.
 *  WebFilter 어노테이션에 URL 경로를 명시하여 해당 요청 시 filter가 동작할 수 있다.
 *  여기서 '/*'로 작성한다면, 이는 모든 요청 경로를 의미한다.
* */
@WebFilter("/first/*")
public class FirstFilter implements Filter {

    public FirstFilter() {

        /* 필기. 기본 생성자 */
        /* 필기. 필터는 Tomcat을 Run하는 시점부터 미리 인스턴스를 생성한다. */
        System.out.println("FirstFilter 인스턴스 생성됨.");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        /* 필기. 필터 인스턴스가 소멸될 때 호출되는 메소드 (= 톰캣 종료 시)*/
        System.out.println("Filter destroy 호출");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        System.out.println("Filter doFilter 호출");

        /* 설명. 다음 필터 또는 서블릿으로 가기 전에 전처리할 코드를 작성한다. */
        /* 설명. FilterChain에서 제공하는 doFilter()를 사용해 다음 필터 또는 서블릿으로 진행시킬 수 있다. */
        filterChain.doFilter(servletRequest, servletResponse);

        /* 설명. 서블릿에서 클라이언트로 가기 전에 후처리할 코드를 작성한다. */
        System.out.println("Servlet 요청 수행 완료!");
    }

    @Override
    public void destroy() {

        /* 필기. 필터 인스턴스가 최초 생성될 때 호출되는 메소드 */
        System.out.println("Filter init 호출");
    }
}
