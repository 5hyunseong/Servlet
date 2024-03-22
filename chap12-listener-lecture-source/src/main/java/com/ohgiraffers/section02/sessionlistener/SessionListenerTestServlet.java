package com.ohgiraffers.section02.sessionlistener;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/session")
public class SessionListenerTestServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		/* 필기. session을 최초 발급받을 시 sessionCreated() 가 동작한다. */
		HttpSession session = req.getSession();
		System.out.println("발급받은 session id : " + session.getId());

		/* 필기. session에 attribute를 추가할 때 attributeAdded() 메소드가 동작한다. */
		session.setAttribute("username", "honggildong");
		session.setAttribute("age", 20);
		session.setAttribute("gender", "M");

		/* 설명. SessionBoundListener 구현 후 테스트 해야 함 */
		session.setAttribute("user", new UserDTO("honggildong", 20));

		/* 필기. session attribute을 동일한 key로 덮어 쓰는 경우 (value 수정) attributeReplaced() 메소드가 동작한다. */
		session.setAttribute("username", "hong");

		/* 필기. session에 attribute를 제거할 때 attributeRemoved()가 동작한다. */
		session.removeAttribute("gender");

		/* 필기. session을 만료하게 되면 sessionDestroyed() 가 동작한다. */
//		session.setMaxInactiveInterval(5);		// 이게 좀 늦게 동작함
		session.invalidate();		//만료 시 모든 attribute를 지운다.

		/* 설명. 추가로 세션 관련 리스너는 HttpSessionActivationListener가 있는데
		 *  세션을 다른 WAS와 연결시키는 작업을 할 시 세션을 옮기기 바로 직전, 직후에 동작하는 메소드를 이용하여
		 *  다른 서버의 두 개 세션 값을 연결하여 작업할 수 있다.
		* */

	}

}