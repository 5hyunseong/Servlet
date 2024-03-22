package com.ohgiraffers.section02.sessionlistener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionActivationListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListenerTest implements HttpSessionListener, HttpSessionAttributeListener {

    public SessionListenerTest() {
        /* 필기. context가 로드될 때 인스턴스가 생성된다. */
        System.out.println("SessionListener: 인스턴스 생성");
    }

    public void sessionCreated(HttpSessionEvent se)  {
        /* 필기. 세션이 생성될 때 동작한다.*/
        System.out.println("SessionListener: session created!!");
        System.out.println("생성된 session id : " + se.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent se)  {
        /* 필기. 세션이 만료될 시 동작한다. */
        System.out.println("SessionListener: session destroyed!!");
    }

    public void attributeAdded(HttpSessionBindingEvent se)  {
        /* 필기. session에 attribute가 추가될 때 동작한다. */
        System.out.println("SessionListener: session attribute added!!");
        System.out.println("session에서 추가된 attr : " + se.getName() + ", " + se.getValue());
    }

    public void attributeRemoved(HttpSessionBindingEvent se)  {
        /* 필기. session에 attribute가 추가될 때 동작한다. */
        System.out.println("SessionListener: session attribute removed!!");
        System.out.println("session에서 제거된 attr : " + se.getName() + ", " + se.getValue());
    }

    public void attributeReplaced(HttpSessionBindingEvent se)  {
        /* 필기. session의 attribute가 갱신될 때 동작한다. */
        System.out.println("SessionListener: session attribute replaced!!");
        System.out.println("session에서 수정된 attr : " + se.getName() + ", " + se.getValue());
    }

}
