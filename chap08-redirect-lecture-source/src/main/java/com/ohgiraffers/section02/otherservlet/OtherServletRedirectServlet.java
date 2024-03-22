package com.ohgiraffers.section02.otherservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/otherservlet")
public class OtherServletRedirectServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("get 요청 정상 수락");
        System.out.println(resp);

        /* 설명. 이후 다룰 RedirectServlet에서 확인해볼 샘플 속성을 request 객체에 저장한다.
         *  여기서 포인트는 이 request 객체를 redirect 시켰을 때, 최종 서블릿에서 과연 해당 속성값을 볼 수 있는지다.
        * */
        req.setAttribute("test", "zxcv");

        resp.sendRedirect("redirect");

    }
}
