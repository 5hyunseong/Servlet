package com.ohgiraffers.section01.forward;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/print")
public class PrintLoginSuccessServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		/* 필기.
		 *  forward 받은 서블릿에서도 요청 방식이 GET이면 doGet 메소드를, 요청 방식이 POST이면 doPost 메소드를 호출한다.
		 *  보내준 서블릿에서 request에 전달 정보를 담았으므로 해당 서블릿에서 사용하기 위해 다시 request에서 꺼내온다.
		* */
		/* 필기.
		 *  forward할 때 전달한 request 및 response 객체의 모든 정보를 이용해 새로운 request, response 객체를 만들고
		 *  그 정보를 이용해 다시 http 메소드에 맞는 서블릿의 doGet 혹은 doPost를 요청하는 방식이다.
		 *  깊은 복사를 이용해 값을 그대로 복사했기 때문에 내부에 존재하는 헤더 정보나 인스턴스는 그대로 유지하고 있다.
		 * */

		String userId = req.getParameter("userId");                 // getParameter()는 String 타입으로 반환
		String password = req.getParameter("password");             // getParameter()는 String 타입으로 반환
		String userName = (String) req.getAttribute("userName");    // getAttribute()는 Object 타입으로 반환

		System.out.println("포워딩된 서블릿으로부터 넘겨 받은 request 객체에 담긴 값 확인:");
		System.out.println("userId: " + userId);
		System.out.println("password: " + password);
		System.out.println("userName: " + userName);

		/* 필기. 응답에 필요한 데이터가 준비되면 동적인 웹 페이지를 생성한다. */
		StringBuilder responseText = new StringBuilder();
		responseText.append("<!doctype html>\n")
				.append("<html>\n")
				.append("<head>\n")
				.append("</head>\n")
				.append("<body>\n")
				.append("<h3 align=\"center\">")
				.append(userName + "(" + userId + ")")
				.append("님 환영합니다.</h3>")
				.append("</body>\n")
				.append("</html>\n");

		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();

		out.print(responseText);
		out.flush();
		out.close();

		/* 필기.
		 *  변수의 디폴트 스코프는 메소드(= 해당 페이지) 스코프이기 때문에 다른 클래스(= 서블릿)와 데이터를 공유할 수 없다.
		 *  하지만 forward 방식은 request 및 response 객체를 포함하여 위임하므로 request에 정보를 저장하여 forward하면
		 *  위임받은 서블릿에서도 위임한 서블릿의 정보를 공유할 수 있다.
		 *  forward 받은 서블릿의 존재를 클라이언트가 알 필요는 없기 때문에 url자체는 변경되지 않는다.
		 *  (사용자는 결과 화면만 제대로 받으면 되기 때문이다.)
		 *  forward 방식의 또 다른 특징은 요청 시 서버로 전송한 데이터가 남아있는 상태로 새로고침(= 재요청)하면
		 *  동일한 요청을 반복하게 되는데, 만약 데이터베이스에 INSERT하는 등의 행위를 하면 의도치 않게 중복된 행이 삽입될 가능성이 있다.
		 *  따라서 그런 경우는 다른 페이지 전환 방식인 sendRedirect를 이용한다 => 프로젝트를 새롭게 만들고 테스트 해보자.
		 * */
	}
}
