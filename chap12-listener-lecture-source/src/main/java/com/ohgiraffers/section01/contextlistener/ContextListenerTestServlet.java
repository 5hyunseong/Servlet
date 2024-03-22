package com.ohgiraffers.section01.contextlistener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/context")
public class ContextListenerTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("context listener 확인용 서블릿");

        ServletContext context = req.getServletContext();

        /* 필기. context에 attr을 추가하면 attributeAdded() 메소드가 동작한다. */
        context.setAttribute("test", "value");

        /* 필기. 동일한 key로 context에 attr을 추가하면(수정하면) attributeReplaced() 메소드가 동작한다. */
        context.setAttribute("test", "value2");

        /* 필기. context에서 attr을 제거하면 attributeRemoved() 메소드가 동작한다. */
        context.removeAttribute("test");
    }
}
