package com.ohgiraffers.section02.formdata;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@WebServlet("/formdata")
public class FormDataTestServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/* Note. Tomcat v9 이하의 환경일 경우 아래 내용 숙지 필요:
		 *  POST 요청으로 전달받은 데이터에 한글이 포함되어 있으면 해당 문자는 인코딩이 맞지 않아 깨진다.
		 *  ------------------------------------------------------------------------------------------------------------
		 *  GET 방식의 데이터는 HTML charset에 기술된 인코딩 방식으로 브라우저가 한글을 이해하고, URLEncoder를 이용해
		 *  %문자로 변환시켜 URL 요청으로 전송한다.
		 *  이 때, 헤더(header)의 내용은 ascii 코드로 전송되기 때문에 어떤 언어이든 서버에 설정된 인코딩 방식과
		 *  일치한다면 해석하는데 문제가 없으므로 한글이 깨지지 않는다.
		 *  GET 요청은 보통 서버의 리소스를 가져오는 행위를 요청하는 http 요청 방식이기에 별도의 데이터가 필요 없어
		 *  요청 본문, 즉 페이로드(payload)는 해석하지 않는다.
		 *  ------------------------------------------------------------------------------------------------------------
		 *  반면, POST 요청은 서버의 기존 리소스에 데이터를 추가하는 요청이기 때문에 요청하는 데이터가 필요한 경우가
		 *  대부분이다. (데이터를 추가하는 목적 외, 다양하게 사용됨.)
		 *  서버의 기존 리소스에 추가해야 하는 정보를 페이로드에 key&value 방식으로 담아 전송하는데, 헤더와는 별개로
		 *  URLEncoder를 이용하지 않고 페이지 meta에 기술된 charset에 따라 UTF-8로 해석된 데이터를 서버로 전송한다.
		 *  기본적으로 서버단에서 페이로드를 디코딩 하는 방식이 별도로 명시되어 있지 않다.
		 *  그래서 request.getCharacterEncoding()을 호출해보면 null을 반환하게 되는데, 인코딩된 방식을 명시하지 않으면
		 *  기본 ISO-8859-1로 해석하므로 값을 꺼내오면 한글인 글자가 깨지는 현상이 발생한다.
		 *  따라서 요청 속에 담긴 parameter를 꺼내오기 전에 parameter값을 해석할 인코딩 방식이 UTF-8임을
		 *  setCharacterEncoding(String encType)으로 지정주어야 브라우저에서 요청한 인코딩 방식과 일치하여
		 *  한글 깨짐 현상을 막을 수 있다.
		 * */

		/* 설명. 기본 설정 인코딩 방식이 UTF-8 이다. */
		System.out.println(req.getCharacterEncoding());

		/* 설명.
		 *  Tomcat v9 이하는 POST 요청일 때 한글이 깨지지 않으려면 UTF-8 인코딩을 설정해줘야 한다.
		 *  현재 Tomcat v10 이상은 아래 작업을 수행하지 않아도 된다.
		* */
//		req.setCharacterEncoding("UTF-8");

		String name = req.getParameter("name");
		System.out.println("name : " + name);

		/* 설명. 인코딩을 제외한 나머지 값들은 GET방식에서 값을 꺼내온 것과 동일하다. */

		/* 설명. 만약 클라이언트 쪽에서 요청한 데이터의 key와 value를 하나하나 확인하기 힘들다면
		 *      모든 데이터의 key를 이용하여 전송된 값들을 일괄 처리할 수 있다.
		* */
		Map<String, String[]> reqMap = req.getParameterMap();
		Set<String> keySet = reqMap.keySet();
		Iterator<String> keyIter = keySet.iterator();

		System.out.println("---------- Key & Value Pair ----------");
		while(keyIter.hasNext()) {
			String key = keyIter.next();
			String[] value = reqMap.get(key);

			System.out.println("key : " + key);
			for(int i = 0; i < value.length; i++) {
				System.out.println("value[" + i + "] : " + value[i]);
			}
		}

		/* 설명.
		 *  파라미터로 전달된 키 목록만도 확인할 수 있다.
		 *  request.getParmeterNames()를 이용한다.
		* */
		System.out.println("---------- Only Key ----------");
		Enumeration<String> names = req.getParameterNames();
		while(names.hasMoreElements()) {
			System.out.println(names.nextElement());
		}

		/* Note.
		 *  GET 방식은 URL을 통해 요청 속 파라미터를 확인하기 편한 반면, POST 요청은 매번 Front(JSP) 코드를 보며
		 *  확인할 수 없으니 이러한 방법으로 Back단에서 요청 속 파라미터를 확인할 수 있음.
		 * */

	}
}
