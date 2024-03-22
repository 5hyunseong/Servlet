package com.ohgiraffers.section01.filter;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/first/filter")
public class FirstFilterTestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("/first/filter 서블릿 요청 확인.");

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        out.println("<!doctype html>");
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>필터 확인용 서블릿 요청 확인 완료!</h3>");
        out.println("</body>");
        out.println("</html>");

        out.flush();
        out.close();
    }

}