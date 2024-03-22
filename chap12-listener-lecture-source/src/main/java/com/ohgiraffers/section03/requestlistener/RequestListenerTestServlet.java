package com.ohgiraffers.section03.requestlistener;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/request")
public class RequestListenerTestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        /* 필기. request에 attribute를 추가할 때 attributeAdded() 메소드가 동작한다. */
        req.setAttribute("username", "honggildong");
        req.setAttribute("age", 20);
        req.setAttribute("gender", "M");

        /* 필기. request에 attribute를 동일한 key로 덮어 쓰는 경우 (value 수정) attributeReplaced() 메소드가 동작한다. */
        req.setAttribute("username", "hong");

        /* 필기. request에 attribute를 제거할 때 attributeRemoved()가 동작한다. */
        req.removeAttribute("gender");

    }

}