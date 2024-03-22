package com.ohgiraffers.section02.uses;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

@WebServlet("/member/register")
public class RegisterMemberServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        /* 설명. 간단한 회원 정보를 입력 받아 회원 가입하기 위한 서블릿 요청을 해보는데, 이 때 크게 두 가지 문제점이 있다.
         *  1. POST 전송 시 한글 깨짐 현상
         *  2. 비밀번호를 암호화 처리
         *  물론 서블릿 쪽에서 작성을 해도 되지만, 서블릿에 요청하기 전에 미리 request에 대한 인코딩 처리나
         *  매번 member 서비스에 사용할 암호화 처리와 같은 공통적인 내용을 서블릿 요청 전에 처리할 수 있다면 유지보수하기 편리할 것이다.
         *  인코딩 필터와 암호화 필터를 이용해 현재 서블릿에 도착하기 전까지 이 두 가지를 해결해 볼 것이다.
         *
         * */
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        System.out.println("userId : " + userId);
        System.out.println("userPwd : " + password);
        System.out.println("name : " + name);

        /* 설명. 요청 시마다 encoding이 변경되는 것을 확인할 수 있다.
         *  이와 동일한 맥락으로 서블릿에 도착하기 전에 암호화 처리를 해보자.
         * */

        /* 설명. 암호화 처리된 패스워드는 동일한 값이 입력되더라도 매번 실행 시마다 다른 값을 가지게 된다.
         *  그럼 나중에 DB에 이 상태로 기록하게 되면 가입된 회원정보로 로그인할 때 비밀번호가 같은지를 어떻게 비교할까?
         *  암호화된 문자열은 일반 문자열 비교가 불가능하고 matches()라는 메소드를 이용해야 한다.
         * */

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("비밀번호가 pass01인지 확인 : " + passwordEncoder.matches("pass01", password));
        System.out.println("비밀번호가 pass02인지 확인 : " + passwordEncoder.matches("pass02", password));

        /* 설명. 이 외에도 필터는 다양한 목적으로 사용이 가능하다.
         *  인증필터, 압축 필터, 리소스 접근 트리거 이벤트 필터, 로깅 필터, 이미지 변환 필터, 토크나이져 필터 등등 다양하게 활용이 가능하다.
         * */
    }
}
