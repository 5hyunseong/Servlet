package com.ohgiraffers.section01.querystring;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/querystring")
public class QueryStringTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        /* 설명.
         *  HttpServlet 클래스의 service method는 요청 방식에 따라 GET 요청에 대해서
         *  doGet() method를 호출하면서 request와 response를 전달한다.
         *  -> 톰캣 서블릿 컨테이너가 요청 url로 매핑된 Servlet클래스의 인스턴스를 생성하여 service method를 호출하고
         *     HttpServlet을 상속받아 오버라이딩한 현재 클래스의 doGet() method가 동적바인딩에 의해 호출된다.
         *  ==========================================================================================================
         *  service로부터 전달받은 HttpServletRequest는 요청 시 전달한 값을 getParameter() method로 추출해 올 수 있다.
         *  이때 인자로 input 태그에 지정한 name속성의 값을 문자열 형태로 전달해주면 된다.
         *  화면에서 전달한 form 내의 모든 input 태그의 값들을 HashMap으로 관리하고 있으므로,
         *  원하는 값을 찾기 위해서는 key역할을 하는 문자열이 필요하기 때문이다.
        * */

        String name = req.getParameter("name");
        System.out.println("이름 : " + name);

        /* 설명.
         *  getParameter는 return타입이 문자열이다.
         *  즉, 숫자를 전달해도 문자열 형태로 전달되므로 숫자 형식의 값이 필요하다면 검증 후에 parsing을 해주면 된다.
        * */
        int age = Integer.parseInt(req.getParameter("age"));
        System.out.println("나이 : " + age);

        /* 설명.
         *  java.sql.Date 타입으로 저장하고 싶은 경우에도 전달된 parameter를 Date type으로 변환해야 한다.
         *  java.sql.Date의 valueOf() 메소드에 전달받은 파라미터를 넘기면 타입을 변환시킨다.
        * */
        Date birthday = Date.valueOf(req.getParameter("birthday"));
        System.out.println("생일 : " + birthday);

        /* 설명.
         *  radioButton으로 전달된 값은 여러 값 중 선택한 하나의 값만 전달되기 때문에
         *  parameter로 전달받은 값을 꺼내기만 하면 된다.(가공처리가 필요한 경우 별도의 처리를 해준다)
        * */
        String gender = req.getParameter("gender");
        System.out.println("성별 : " + gender);

        /* 설명. selectbox를 이용한 방식도 radioButton 방식 유사하다. */
        String national = req.getParameter("national");
        System.out.println("국적 : " + national);

        /* 설명.
         *  checkbox는 다중으로 입력을 받을 수 있어, 선택된 값이 문자열 배열로 전달된다.
         *  이 때 getParameterValues() method를 이용하여 문자열 배열 형태로 값을 반환받을 수 있다.
        * */
        System.out.println("취미 : ");
        String[] hobbies = req.getParameterValues("hobbies");

        for(String hobby : hobbies) {
            System.out.println(hobby);
        }

        /* 설명.
         *  form 내부의 값을 입력하지 않는 등의 에러들은 추후에 해결하고
         *  우선 이 챕터에서는 parameter로 get방식 요청이 오는 경우 서블릿에서 값을 전달받는 기능을 확인해 본다.
        * */
        /* 설명. index의 a태그 href 속성을 이용하여 직접 쿼리스트링 문자열을 생성하여 전달해도 동일한 결과가 나온다. */
    }
}
