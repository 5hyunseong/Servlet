package com.ohgiraffers.section02.headers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import static java.util.Locale.KOREA;

@WebServlet("/headers")
public class ResponseHeaderPrintServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/* 설명. 서버의 현재 시간을 동적인 페이지로 만들어 클라이언트 측으로 내보내보자. */
		resp.setContentType("text/html");                   // Tomcat v10 이상
//		resp.setContentType("text/html; charset=UTF-8");    // Tomcat v9 이하

//		resp.setHeader("Refresh", "1");

		PrintWriter out = resp.getWriter();

        /* 설명. 현재 시간을 생성. */
//		long currentTime = System.currentTimeMillis();  // 간단한 출력이지만 사람이 읽기 어려움.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", KOREA);
        String currentTime = sdf.format(new Date());

		out.print("<h1>" + currentTime + "</h1>");

		out.flush();
		out.close();

		/* 필기. response 객체의 header값에 대한 key값과 value값을 한 번에 확인해보기. */
		Collection<String> responseHeaders = resp.getHeaderNames();
		Iterator<String> iter = responseHeaders.iterator();
		while(iter.hasNext()) {
			String headerName = iter.next();
			System.out.println(headerName + " : " + resp.getHeader(headerName));
		}
	}
}
