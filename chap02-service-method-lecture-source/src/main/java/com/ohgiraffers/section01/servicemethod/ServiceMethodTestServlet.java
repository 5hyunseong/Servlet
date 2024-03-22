package com.ohgiraffers.section01.servicemethod;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/request-service")
public class ServiceMethodTestServlet extends HttpServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {

        /* 설명. 나중에 HTTP를 대체할 프로토콜이 존재한다면 HttpServletRequest는 다른 클래스로 대체되어야 한다.
         *      따라서 ServletRequest라는 추상화된 타입으로 사용하고, 실제 인스턴스는 HttpServletRequest로 사용하여
         *      나중에 service method의 인자 타입은 변경하지 않고 다른 프로토콜을 사용하는 Request로 변경이 가능하다.(다형성)
         *      =================================================================================================
         *      하지만 현재 사용되는 프로토콜은 HTTP 프로토콜로, HttpServletRequest 타입이다.
         *      HttpServletRequest는 ServletRequest 타입을 상속받아서 구현하였으며, HTTP 프로토콜의 정보를 담고 있기 때문에
         *      실제 사용 시에는 HttpServletRequest 타입으로 다운캐스팅 해서 사용해야 한다.
         *      ServletResponse도 같은 맥락이다.
         *      =================================================================================================
         *      HttpServlet 클래스의 service(ServletRequest request, ServletResponse) method에서는
         *      인자로 전달받은 request와 response를 HttpServletRequest와 HttpServletResponse로 다운캐스팅 한다.
        * */
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        /* 설명. 하위 타입으로 다운캐스팅 한 후 요청받은 http 메소드가 어떤 메소드인지를 판단한다.
         * 필기. httpRequest의 getMethod() method는 어떠한 http method 요청이 들어온 것인지 문자열 형태로 반환한다.
        * */
        String httpMethod = httpRequest.getMethod();

        /* 설명. 요청 내용을 한 번 출력해보자
         * 필기. a태그의 href 속성은 화면의 url 내용을 변경하는 방식으로, GET 요청에 해당한다.
        * */
        System.out.println("http method : " + httpMethod);		//GET

        if(("GET").equals(httpMethod)) {

            /* 필기. GET 요청을 처리할 메소드로 요청과 응답 정보를 전달한다. */
            doGet(httpRequest, httpResponse);

        } else if(("POST").equals(httpMethod)) {

            /* 필기. POST 요청을 처리할 메소드로 요청과 응답 정보를 전송한다. */
            doPost(httpRequest, httpResponse);
        }
        /* 설명.
         *  이외에도 http 메소드는 GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE, CONNECT가 있다.
         *  하지만 주로 사용되는 메소드는 GET과 POST이다.
         *  따라서 GET과 POST에 대한 처리만 간략하게 해본다.
         * */
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GET 요청을 처리할 메소드 호출");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST 요청을 처리할 메소드 호출");
    }
}
