package com.ohgiraffers.section01.othersite;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/othersite")
public class OtherSiteRedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("get 요청 후 naver 사이트로 redirect");

        /* 설명. 개발자 도구(F12) 모니터링
         *  브라우저의 개발자 도구의 network 탭을 보면 302 응답 코드와 함께 NAVER 홈페이지로 이동하는 것을 볼 수 있다.
         *  사용자 url 재작성이라고 불리는 redirect 방식은 302번 응답 코드인 경우 요청에 대한 처리를 완료하였고,
         *  사용자의 url을 강제로 redirect 경로로 이동시키라는 의미이다.
         *  응답 헤더 작성은 General Header의 302번 코드와 esponse header의 Location 헤더값에 redirect할 경로를 포함하여 응답한다.
        * */
        resp.sendRedirect("http://www.naver.com");
    }
}
