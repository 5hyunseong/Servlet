package com.ohgiraffers.section01.contextlistener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListenerTest implements ServletContextListener, ServletContextAttributeListener {

    /* 필기. 리스너는 특정 이벤트 행위가 동작할 때 컨테이너에 의해 호출되는 메소드를 인터페이스에 정의해두었다.
     *  이를 상속받아 구현한 메소드가 특정 이벤트 발생에 대해 필요한 로직을 작성하는데, 크게 3가지 범주의 이벤트로 분류한다.
     *  1. context (-> 톰캣 컨테이너 자체에 리스너 연결)
     *    1) ServletContextListener : 웹애플리케이션의 시작, 종료에 대한 이벤트 리스너
     *    2) ServletContextAttributeListener : context에 attribute를 추가, 제거, 수정에 대한 이벤트 리스너
     *  2. session (-> 세션에서 발생 가능한 이벤트)
     *    1) HttpSessionListener : Http 세션의 시작, 종료에 대한 이벤트 리스너
     *    2) HttpSessionAttributeListener : HttpSession에 attribute0 추가, 제거 , 수정에 대한 이벤트 리스너
     *  3. request (-> request 관련 이벤트)
     *    1) ServletRequestListener : ServletRequest 생성과 소멸에 대한 이벤트 리스너
     *    2) ServletRequest에 attribute를 추가, 제거, 수정하는 것에 대한 이벤트 리스너
     * */
    public ContextListenerTest() {

        /* 필기. context가 생성될 때 변화를 감지하는 Listener 인스턴스가 함께 생성된다. */
        System.out.println("ContextListener: 인스턴스 생성");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        /* 필기. context에 attribute가 추가될 때 동작한다. */
        System.out.println("ContextListener: context attribute added!");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        /* 필기. context에 attribute가 제거될 때 동작한다. */
        System.out.println("ContextListener: context attribute removed!");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        /* 필기. context에 attribute가 변경될 때 동작한다. */
        System.out.println("ContextListener: context attribute replaced!");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* 필기. context가 최초 생성될 때 생성자 호출 이후 동작하는 메소드이다. */
        System.out.println("ContextListener: context init!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* 필기. context가 소멸할 때 동작한다. */
        System.out.println("ContextListener: context destroy!");
    }
}
