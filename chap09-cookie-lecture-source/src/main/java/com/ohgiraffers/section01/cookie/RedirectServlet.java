package com.ohgiraffers.section01.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");

		System.out.println("firstName : " + firstName);
		System.out.println("lastName : " + lastName);

		/* 설명. 출력해 보면 null값이 나오는 것을 확인할 수 있다.
		 *  따라서 redirect를 사용하면 request 객체는 서블릿 간에 공유되지 않는 것을 확인할 수 있다.
		 *  다시 이전 서블릿에서 쿠키라는 것을 생성하고 해당 서블릿에서 쿠키를 이용해 값을 꺼내보자
		* */

		/* 필기. 쿠키를 사용하는 방법도 간단하다.
		 *  1. request에서 쿠키 목록을 쿠키 배열 형태로 꺼내온다.
		 *  2. Cookie의 getName()와 getValue()를 이용해 쿠키에 담긴 값을 사용한다.
		 * */
		Cookie[] cookies = req.getCookies();

//        for(int i = 0; i < cookies.length; i++) {
//
//            System.out.println("[cookie] " + cookies[i].getName() + " : " + cookies[i].getValue());
//
//            if("firstName".equals(cookies[i].getName())) {
//                firstName = cookies[i].getValue();
//            } else if("lastName".equals(cookies[i].getName())) {
//                lastName = cookies[i].getValue();
//            }
//        }

        for (Cookie c : cookies) {

			System.out.println("[cookie] " + c.getName() + " : " + c.getValue());

			if ("firstName".equals(c.getName())) {
				firstName = c.getValue();
			} else if ("lastName".equals(c.getName())) {
				lastName = c.getValue();
			}
		}

		StringBuilder responseText = new StringBuilder();
		responseText.append("<!doctype html>\n")
				.append("<html>\n")
				.append("<head>\n")
				.append("</head>\n")
				.append("<body>\n")
				.append("<h3>your first name is ")
				.append(firstName)
				.append(", and last name is ")
				.append(lastName)
				.append(".")
				.append("</h3>")
				.append("</body>\n")
				.append("</html>\n");

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.print(responseText);

		out.flush();
		out.close();

		/* 필기. 쿠키는 텍스트 파일 형태로 클라이언트 컴퓨터에 저장된다.
		 *  따라서 개인 PC는 크게 상관 없지만 공용 PC 등 다른 사용자와 함께 쓰는 컴퓨터일 경우(민감한 개인정보를 포함된 경우) 보안에 취약하다.
		 *  따라서 민감한 개인 정보를 취급하는 경우에는 쿠키보다는 세션을 이용한다.
		 *  세션은 쿠키와 유사한 형태로 key=value 쌍으로 저장되지만 서버(톰캣)에서 관리되므로 보안에 더 우수하다는 장점을 가진다.
		* */

	}
}
