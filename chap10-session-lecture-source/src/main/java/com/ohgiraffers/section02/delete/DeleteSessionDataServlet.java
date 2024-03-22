package com.ohgiraffers.section02.delete;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/delete")
public class DeleteSessionDataServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

		/* 필기. 세션 객체를 다시 반환받아 세션에 담긴 Attribute 목록을 출력해 보면
		 *  이전 section에서 담았던 firstName과 secondName이 있는 것을 확인할 수 있다.
		* */
		HttpSession session = req.getSession();
		Enumeration<String> sessionNames = session.getAttributeNames();
		while(sessionNames.hasMoreElements()) {
			System.out.println(sessionNames.nextElement());
		}

		/* 필기. 세션의 데이터를 지우는 방법은 여러 가지가 있다.
		 *  1. 설정한 만료시간이 지나면 세션이 만료된다. (xml 설정도 가능)
		 *  2. removeAttribute()로 session의 Attribute를 지운다.
		 *  3. invalidate()를 호출하면 세션의 모든 데이터가 제거된다.
		* */

		/* 설명. 시간설정은 앞에서 해봤으니 removeAttribute를 해보면 firstName은 지워진 것을 볼 수 있다. */
		session.removeAttribute("firstName");
		sessionNames = session.getAttributeNames();
		while(sessionNames.hasMoreElements()) {
			System.out.println(sessionNames.nextElement());
		}

		/* 필기. invalidate를 호출하면 세션 자체를 무효화 했기 때문에 이후 세션을 이용하는 것은 에러가 발생한다.
		 *  즉, 세션 내의 데이터를 지우는 것 뿐 아니라 세션을 강제로 만료시켰다고 볼 수 있다.
		* */
		session.invalidate();
		sessionNames = session.getAttributeNames();
		while(sessionNames.hasMoreElements()) {
			System.out.println(sessionNames.nextElement());
		}

	}
}
