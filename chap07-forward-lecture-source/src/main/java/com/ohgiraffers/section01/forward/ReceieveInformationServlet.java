package com.ohgiraffers.section01.forward;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/forward")
public class ReceieveInformationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        /* 설명.
         *  서블릿이 하는 역할은 3가지는
         *  1. 요청 정보 받기
         *  2. 비지니스 로직 처리
         *  3. 응답 내보내기
         * */

        /* 설명. 클라이언트로부터 받아온 request 객체에서 요청 정보를 받는다. */
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        System.out.println("userId : " + userId);
        System.out.println("password : " + password);

        /* 설명.
         *  원래라면 이 부분에서 userId와 password를 가지고 해당 정보와 일치하는 회원이 DB에 존재하는지를 조회하는
         *  비즈니스 로직(BL)이 수행되어야 한다.(이는 향후 MVC에서 다룰 예정)
         *  우리는 DB에서 제대로 조회되었다는 가정 하에 'xxx님 환영합니다.'와 같은 메시지를 출력하는 화면을 만들어 응답해보자.
         *  (PrintLoginSuccessServlet을 추가)
        * */

        /* 필기. 위의 userId와 password는 이미 request 객체에 담겨있던 속성을 getAttribute()로 꺼냈다면,
         *  아래는 요청을 받고 나서 요청을 처리중에 사용자가 원하는 데이터를 setAttribute()로 저장한 것이다. */
        req.setAttribute("userName", "홍길동");

        /* 필기. setAttribute()와 getAttribute()
         *  setAttribute():
         *   - 요청 범위(request 객체)에 데이터를 속성(attribute)으로 추가.
         *   - 사용자 인증 정보, form 입력값 처리 이후의 결과, 요청 처리 시간 등을 저장할 때 사용.
         *  getAttribute():
         *   - 요청 범위(request 객체)에 데이터를 속성(attribute)으로 검색.
         *   - 검색 결과는 Object 타입으로 반환함.
         *   - 요청 처리에 사용하기 위해 저장된 속성 값을 검색, 다음 페이지나 뷰로 전달할 때 사용.
         * */

        /* 필기. 어떤 서블릿으로 위임(포워딩)할 것인지 대상 서블릿을 지정하는 인스턴스(RequestDispatcher)를 생성하고,
         *  forward() 메서드를 통해 요청과 응답에 대한 정보를 전달하여 나머지 작업을 수행하도록 위임한다.
         *
         * 필기. RequestDispatcher:
         *  - 서블릿 간에 요청을 전달(포워딩)하거나, 요청에 대한 응답을 HTML 페이지나 다른 서블릿 또는 JSP로
         *    포함(include)할 때 사용되는 객체.
         *  - 즉, 서블릿, HTML 파일, JSP 등 다른 웹 리소스에 요청을 위임하는 주체다.
         *  - 참고로 Dispatcher는 배차 담당자라는 뜻.
        * */

        RequestDispatcher rd = req.getRequestDispatcher("print");   // print라는 이름을 가진 서블릿으로 요청을 전달할 인스턴스 생성.
        rd.forward(req, resp);  // 인스턴스는 request 및 response 객체를 가지고 본인이 가야 할 다른 서블릿으로 전달된다.
    }
}
