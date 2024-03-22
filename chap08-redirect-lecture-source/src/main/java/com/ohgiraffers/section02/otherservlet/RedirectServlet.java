package com.ohgiraffers.section02.otherservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("이 서블릿으로 redirect 성공!");

        /* 설명.
         *  forward와는 달리, redirect 시에는 request의 attribute 개념으로 다른 서블릿에게 데이터(상태)를 전달할 수 없다.
         *  왜냐하면 서블릿이 클라이언트로 redirect 응답을 보낼 때 자신이 가지고 있던 request 및 response 객체는 소멸되고,
         *  redirect 응답을 받은 클라이언트가 요청할 때 새로운 request 및 response 객체를 생성해 요청하기 때문이다.
        * */
		System.out.println("Redirect 이후의 서블릿에서 attribute 검색: " + req.getAttribute("test"));     // null 출력

        /* Note. 굳이 넘기고 싶다면 attribute가 아닌 parameter를 이용하는 방법이 있긴 하다.
         *  앞의 서블릿에서 클라이언트에게 redirect를 요청할 때 다음과 같이 쿼리 스트링을 사용해 작성하면 된다.
         *  resp.sendRedirect("redirect?test=zxcv");
         * */

		/* 설명. 현재 페이지로 redirect를 성공하면 보여줄 페이지를 작성해보자 */
		StringBuilder redirectText = new StringBuilder();
		redirectText.append("<!doctype html>\n")
				.append("<head>\n")
				.append("</head>\n")
				.append("<body>\n")
				.append("<h1>이 서블릿으로 redirect 성공!</h1>")
				.append("</body>\n")
				.append("</html>\n");

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.print(redirectText);

		out.flush();
		out.close();

        /* 필기. Forward vs. Redirect
         *  - request 또는 response 객체를 유지시켜 요청을 처리하고자 한다면 forward를 사용.
         *  - 클라이언트(브라우저)가 페이지 새로 고침을 할 때마다 처음 요청하는 서블릿을 다시 호출하는 상황(DB INSERT 등)을
         *    막고자 한다면 redirect를 사용.
        * */

		/* 설명. 그렇다면 redirec시 값을 유지해서 사용하는 방법은 무엇일까?
		 *  크게 쿠키(cookie)와 세션(session) 이라는 것으로 이용할 수 있다. (다음 챕터에서 확인해보자)
		 * */
	}
}
