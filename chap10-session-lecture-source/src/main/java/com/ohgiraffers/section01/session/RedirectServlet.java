package com.ohgiraffers.section01.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("RedirectServlet 호출됨.");

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        System.out.println("firstName : " + firstName);
        System.out.println("lastName : " + lastName);

        /* 설명. 앞서 작성한 페이지와 동일한 세션 아이디를 반환한다. */
        HttpSession session = req.getSession();
        System.out.println("redirect 페이지 session id : " + session.getId());

        /* 필기. 세션에 담긴 모든 Attribute 키 목록을 반환받을 수 있다. */
        Enumeration<String> sessionNames = session.getAttributeNames();

        while(sessionNames.hasMoreElements()) {
            System.out.println(sessionNames.nextElement());
        }

        /* 필기. 동일한 아이디를 가진 세션에서는 setAttribute한 값을 getAttribute로 꺼내올 수 있다. */
        firstName = (String) session.getAttribute("firstName");
        lastName = (String) session.getAttribute("lastName");

        /* 필기. 꺼내온 값을 이용해 페이지에 응답용 html을 내보낸다. */
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        /* 설명. StringBuilder 대신 PrintWriter의 println() 함수를 사용해 출력할 수도 있다. */
        out.println("<!doctype html>");
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Your first name is ");
        out.println(firstName);
        out.println(" and last name is ");
        out.println(lastName);
        out.println("</h3>");
        out.println("</body>");
        out.println("</html>");

        out.flush();
        out.close();

    }
}
