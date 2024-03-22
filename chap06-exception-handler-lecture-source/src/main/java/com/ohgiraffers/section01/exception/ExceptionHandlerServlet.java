package com.ohgiraffers.section01.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/showErrorPage")
public class ExceptionHandlerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

        /* Note. request 객체는 사실상 하나의 요청부터 응답까지 유지되는 임시 저장 공간이기도 하다.
         *  setAttribute()라는 메서드를 사용해 이 공간에 값을 담을 수 있고,
         *  getAtrribute()라는 메서드를 사용해 key-value 쌍으로 다시 값을 추출할 수 있다.
        * */

        System.out.println("Exception Handler 서블릿 호출됨.");

		/* 설명. request 객체의 attribute에는 에러와 관련된 정보가 들어있다. 이를 반복문으로 확인해본다. */
		Enumeration<String> attrName = req.getAttributeNames();
		while(attrName.hasMoreElements()) {
			System.out.println(attrName.nextElement());
		}

		/* 설명. 출력되는 내용은 아래와 같다.
		 *  jakarta.servlet.forward.request_uri
		 *  jakarta.servlet.forward.context_path
		 *  jakarta.servlet.forward.servlet_path
		 *  jakarta.servlet.forward.mapping
		 *  jakarta.servlet.error.message
		 *  jakarta.servlet.error.servlet_name
		 *  jakarta.servlet.error.request_uri
		 *  jakarta.servlet.error.status_code
		* */

		/* Note. 아래 중 statusCode, message, servletName 정도만 해보고 나머지는 복붙 또는 건너뛰어도 됨. */
		/* 설명. request의 attribute에 에러와 관련된 것들을 활용해 에러 전용 페이지를 동적으로 만들어 응답해본다. */
		String forwardRequestURI = (String) req.getAttribute("jakarta.servlet.forward.request_uri");
		String contextPath = (String) req.getAttribute("jakarta.servlet.forward.context_path");
		String servletPath = (String) req.getAttribute("jakarta.servlet.forward.servlet_path");
		HttpServletMapping mapping = req.getHttpServletMapping();
		Integer statusCode = (Integer) req.getAttribute("jakarta.servlet.error.status_code");
		String message = (String) req.getAttribute("jakarta.servlet.error.message");
		String servletName = (String) req.getAttribute("jakarta.servlet.error.servlet_name");
		String errorRequestURI = (String) req.getAttribute("jakarta.servlet.error.request_uri");

		System.out.println(forwardRequestURI);
		System.out.println(contextPath);
		System.out.println(servletPath);
		System.out.println(mapping);
		System.out.println(mapping.getServletName());
		System.out.println(mapping.getMatchValue());
		System.out.println(mapping.getPattern());
		System.out.println(mapping.getMappingMatch());
		System.out.println(statusCode);
		System.out.println(message);
		System.out.println(servletName);
		System.out.println(errorRequestURI);

		StringBuilder errorPage = new StringBuilder();
		errorPage.append("<!doctype html>\n")
				.append("<html>\n")
				.append("<head>\n")
				.append("</head>\n")
				.append("<body>\n")
				.append("<h1>")
				.append(statusCode)
				.append(" - ")
				.append(message)
				.append("<br>\n")
				.append("<p>에러 발생한 서블릿 이름: ")
				.append(servletName)
				.append("</p>")
				.append("</h1>\n")
				.append("</body>\n")
				.append("</html>");

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.print(errorPage);
		out.flush();
		out.close();
	}
}
