package com.ohgiraffers.section01.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/response")
public class ResponseTestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		/* 필기.
		 *  서블릿의 주요 역할은 크게 3가지이다.
		 *  1. 요청 받기: HTTP method GET/POST 요청에 따라 parameter로 전달받은 데이터를 꺼내올 수 있다.
		 *  2. 비지니스 로직 처리: DB접속과 CRUD에 대한 로직 처리 시작 부분 -> 서비스 계층을 호출해 해결한다.(MVC2구조 기반)
		 *  3. 응답하기: 문자열로 동적인 웹 페이지(HTML 태그)를 생성하고 스트림을 통해 내보낸다.(SSR 방식)
		 *  해당 챕터에서는 3번째 역할, 즉 Stream을 통해 사용자 브라우저로 페이지를 내보내는 것을 해 볼 것이다.
		* */

		/* 필기. 문자열을 이용해 사용자에게 내보낼 페이지를 작성한다.
		 *  이처럼 서버측에서 클라이언트에게 보여줄 페이지를 작성하는 방식을 Server-Side Rendering(SSR)이라 부른다.
		* */
		StringBuilder respBuilder = new StringBuilder();
		respBuilder.append("<!doctype html>\n")
				.append("<html>\n")
				.append("<head>\n")
				.append("</head>\n")
				.append("<body>\n")
				.append("<h1>안녕 servlet response</h1>\n")   // 처음에는 'hello'로 해놓고 뒤에서 '안녕'으로 바꿀 예정.
				.append("</body>\n")
				.append("</html>");
		/* Note.
		 *  Tomcat v9 이하일 때에는 MIME 타입과 인코딩을 둘 다 설정하는 작업이 필요함.
		 *  그러나, Tomcat v10부터는 MIME 타입만 설정해도 인코딩 방식은 UTF-8로 적용됨.
		 *  Stream을 생성해 출력하는 부분은 두 버전 모두 동일.
		 * */

		/* Note. Tomcat v9 이하일 때: */
		/* 필기.
		 *  1. MIME 타입 설정: 클라이언트 브라우저로 내보낼 데이터의 타입을 응답 헤더에 설정하는데,
		 *     content-type 헤더의 값을 지정해주지 않으면 null이 기본 값이므로 content-type 값을 정의해야 한다.
		* */
//		System.out.println("default response type : " + resp.getContentType());

		/* 필기. contentType을 text/plain으로 설정하면 브라우저 측에서는 HTML 태그를 태그가 아닌 문자열로 인식한다. */
//		resp.setContentType("text/plain");
		/* 필기. 브라우저 측에서는 content-type이 text/html로 설정되어야 정상적으로 HTML 태그를 인식하고 페이지를 그릴 수 있다. */
//		resp.setContentType("text/html");

		/* 필기. 2. 인코딩 설정: 응답 시에도 별도 인코딩을 지정해주지 않으면 기본 설정값(UTF-8)을 따른다. */
//		System.out.println("default response encoding : " + resp.getCharacterEncoding());

		/* 필기. 인코딩 방식을 명시적으로 변경할 수 있으며, contentType과 인코딩 설정을 동시에 진행할 수도 있다.
		 *  인코딩을 설정할 때 주의할 점은 반드시 getWriter()로 Stream을 얻어오기 전에 설정해야 한다.
		* */
		/* Note. 참고로 Tomcat v10 이상은 인코딩 설정 전에 stream을 얻어 와도 에러가 발생하지 않음. */
//		resp.setCharacterEncoding("UTF-8");					// 인코딩 설정
//		resp.setContentType("text/html; charset=UTF-8");	// content-type 설정 + 인코딩 설정
//		System.out.println("changed response encoding : " + resp.getCharacterEncoding());	// 변경된 인코딩 확인

		/* Note. Tomcat v10 이상일 때: */
		/* 필기. 1. MIME 타입 설정: MIME 타입만 명시해도 인코딩 설정은 자동으로(UTF-8) 적용됨 */
		resp.setContentType("text/html");

		/* Note. Tomcat 버전과 관계 없이 stream을 생성, 이를 통해 문자열 출력 후 리소스 반환 */
		/* 필기.
		 *  클라이언트 브라우저로 응답하기 위해서는 HttpServletResponse의 getWriter() method로 PrintWriter 인스턴스를 반환받는다.
		 *  PrintWriter는 BufferedWriter와 형제격인 클래스이지만 더 많은 형태의 생성자를 제공하고 있는 범용성으로 인해 더 많이 사용된다.
		* */
		PrintWriter out = resp.getWriter();

		/* 필기. 스트림을 이용해 내보낸다. */
//		out.print(respBuilder.toString());	// Tomcat v9 이하
		out.print(respBuilder);				// Tomcat v10 이상

		/* 필기. 스트림이 사용한 리소스를 반환한다. */
		out.flush();	// 버퍼에 잔류한 데이터를 강제로 비운다.
		out.close();	// 스트림을 닫는다.
	}
}
