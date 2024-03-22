package com.ohgiraffers.section01.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/session")
public class SessionHandlerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        /* 설명. 이전 챕터에서는 데이터를 공유하기 위한 기술로 클라이언트 측에서 관리되는 cookie를 이용했다.
         *  하지만, 쿠키의 보안상 단점과 지원하지 않는 브라우저 문제 등으로 상태를 유지해야 하는 메커니즘에 적합하지 않은 경우가 다수 있다.
         *  특히 회원 정보를 이용해서 해당 회원의 로그인 상태를 지속적으로 유지해야 하는 경우가 특히 그렇다(보안에 매우 취약함).
         *  따라서 클라이언트측이 아닌 서버측에서 조금 더 안전하게 관리되는 세션(session)이라는 인스턴스를 이용해서 상태를 유지하는 메커니즘을 제공하고 있다.
        * */

        /* 설명. 전반적인 흐름은 앞서 배운 Cookie와 동일하다. */
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        System.out.println("firstName : " + firstName);
        System.out.println("lastName : " + lastName);

        /* 설명. redirect하면 RedirectServlet에서 getParameter 하는 경우 값이 null로 나온다.
         *      이유는 이전 챕터와 동일하다.
         * */
//		resp.sendRedirect("redirect");

        /* 설명. 클라이언트로부터 서블릿으로 요청이 들어올 때, HttpServletRequest의 Header에는 JSESSIONID라는 값이 같이 포함되어 넘어온다.
         *  (이 JSESSIONID는 클라이언트와 서버 간의 세션을 식별하는 데 사용되는 고유 식별자라고 보면 된다)
         *  그러면 서블릿(=서버, =Tomcat)은 이 JSESSIONID에 해당하는 HttpSession 객체를 검색한다.
         *  이 때, 서버에 이와 매칭되는 세션 객체가 이미 존재한다면 기존 세션 객체를 재사용하고, 세션 객체가 없을 때만 새로 생성한다.
         *  (일반적으로 요청 헤더에 JSESSIONID가 없는 경우, 서블릿 컨테이너는 자동으로 HttpSession 객체를 생성하고 새로운 JSESSIONID를 발급해 클라이언트에게 반환한다.)
         *  이제야 서버는 사용자별로 데이터를 저장하고 관리할 수 있는 전용 공간을 제공할 수 있게 되었으며
         *  이 공간을 사용해 사용자는 웹앱과 상호 작용하는 동안 일관된 사용자 경험을 유지할 수 있다.
        * */
        HttpSession session = req.getSession();

        /* 설명. 세션은 강제로 만료시킬 수 있는 기능도 있지만 만료시간을 설정해 주는 것이 좋다.
         *  설정된 기본 시간은 30분이다. 필요에 따라 늘리거나 줄이면 된다.
        * */
        System.out.println("session default 유지 시간 : " + session.getMaxInactiveInterval());  // 1800

        /* 설명. 세션 만료 시간을 10분으로 설정했다. */
        session.setMaxInactiveInterval(60 * 10);
        System.out.println("변경 후 session 유지 시간 : " + session.getMaxInactiveInterval());

        /* 설명. RedirectServlet에서 확인할 세션 ID 확보 */
        System.out.println("session id : " + session.getId());

        /* 설명. 세션은 redirect를 해도 값을 유지할 수 있으므로, request보다 더 넓은 범위의 공유 영역이라고 표현할 수 있다.
         *  세션에 값을 담을 때 setAttribute(String key, Object value) 형태로 담을 수 있고,
         *  값을 꺼낼 때에는 key를 통해 getAttribute(key)로 꺼낼 수 있다.
        * */
        session.setAttribute("firstName", firstName);
        session.setAttribute("lastName", lastName);

        /* 설명. 다시 리다이렉트 후 redirect 서블릿에서 세션에서 값을 꺼낸 후 확인해보자 */
        resp.sendRedirect("redirect");
    }
}
