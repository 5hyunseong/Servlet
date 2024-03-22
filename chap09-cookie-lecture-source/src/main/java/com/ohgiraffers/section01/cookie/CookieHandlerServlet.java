package com.ohgiraffers.section01.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cookie")
public class CookieHandlerServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");

		System.out.println("firstName : " + firstName);
		System.out.println("lastName : " + lastName);

		/* 설명.
		 *  form으로 전송된 데이터를 변수에 저장하고 다른 servlet으로 redirect를 하면 parameter로 꺼내온 값은 null이 된다.
		 *  redirect될 servlet을 만들고 redirect를 발생시켜서 확인해보자.
		* */

		/* 설명. redirect는 url을 재작성하고, url을 이용해 요청하는 방식이므로 get방식의 요청이다.
		 *      따라서 redirect되는 서블릿은 doGet메소드쪽에서 처리해야 한다.
		* */
        
//		resp.sendRedirect("redirect");  // redirect 테스트 후 주석처리

		/* 필기. 쿠키를 사용하는 방법은 간단하며, 쿠키를 사용하는 절차는 다음과 같다. */
		/*  1. 쿠키를 생성한다.
		 *  2. 생성한 쿠키의 만료 시간을 설정한다.
		 *  3. 응답 헤더에 쿠키를 담는다.
		 *  4. 응답을 보낸다.
		 *
		 * 필기. 하지만 쿠키는 일부 제약사항이 있다.
		 *  쿠키의 이름은 ascii 문자만을 사용해야 하며 한 번 설정한 쿠키의 이름은 변경할 수 없다.
		 *  또한 쿠키의 이름에는 공백문자와 일부 특수문자([ ] ( ) = , " \ ? @ : ;)를 사용할 수 없다.
		* */
		Cookie firstNameCookie = new Cookie("firstName", firstName);	//기본 생성자를 제공하지 않는다.
		Cookie lastNameCookie = new Cookie("lastName", lastName);

		firstNameCookie.setMaxAge(60 * 60 * 24);				// 필기. 초 단위 설정이다. (하루를 만료 시간으로 두는 경우 예시이다.)
		lastNameCookie.setMaxAge(60 * 60 * 24);

		resp.addCookie(firstNameCookie);
		resp.addCookie(lastNameCookie);

		resp.sendRedirect("redirect");
	}
}
