package com.ohgiraffers.section01.xml;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

public class LifeCycleTestServlet extends HttpServlet {

    /* 필기. 각 메소드의 호출 횟수를 카운트 할 목적의 필드 */
    private int initCount = 1;
    private int serviceCount = 1;
    private int destroyCount = 1;

    /* 필기. 기본 생성자 */
    public LifeCycleTestServlet() {
        System.out.println("xml 방식 기본 생성자 실행");
    }

    /* 필기. 최초 서블릿 요청 시 동작하는 메소드 */
    public void init(ServletConfig config) throws ServletException {
        /* 필기. 서블릿 컨테이너에 의해 호출되며 최초 요청시에만 실행하고 두 번째 요청부터는 호출하지 않음! */
        System.out.println("xml 매핑 init() 메소드 호출됨: " + initCount++);
    }

    public void destroy() {
        /* 필기. 컨테이너가 종료될 때 호출되는 메소드며, 주요 자원 반납을 목표로 사용된다.*/
        System.out.println("xml 매핑 destroy() 메소드 호출됨: " + destroyCount++);
    }

    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {

        /* 필기.
         *  서블릿 컨테이너에 의해 호출되며 최초 요청시에는 init() 이후에 동작하고,
         *  두 번째 요청부터는 init() 호출 없이 바로 service()를 호출한다.
        * */
        System.out.println("xml 매핑 service() 메소드 호출됨: " + serviceCount++);
    }


}
