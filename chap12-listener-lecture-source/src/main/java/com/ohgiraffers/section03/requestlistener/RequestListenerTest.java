package com.ohgiraffers.section03.requestlistener;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class RequestListenerTest implements ServletRequestListener, ServletRequestAttributeListener {

    public RequestListenerTest() {
        /* 필기. context가 로드될 떄 생성자 호출하여 인스턴스는 생성된다. */
        System.out.println("RequestListener: 인스턴스 생성");
    }

    public void requestDestroyed(ServletRequestEvent sre)  {
        /* 필기. request가 소멸될 때 호출된다. */
        System.out.println("RequestListener:request destoryed!!");
    }

    public void attributeRemoved(ServletRequestAttributeEvent srae)  {
        /* 필기. request에 attribute가 제거될 때 호출된다. */
        System.out.println("RequestListener:request attribute removed!!");
    }

    public void requestInitialized(ServletRequestEvent sre)  {
        /* 필기. request가 생성될 때 호출된다. */
        System.out.println("RequestListener:request init!!");
    }

    public void attributeAdded(ServletRequestAttributeEvent srae)  {
        /* 필기. request에 attribute가 추가될 때 호출된다. */
        System.out.println("RequestListener:request attribute added!!");
    }

    public void attributeReplaced(ServletRequestAttributeEvent srae)  {
        /* 필기. request에 attribute가 갱신될 때 호출된다. */
        /* 설명. org.apache.catalina.ASYNC_SUPPORTED라는 attribute가 자동 수정되기 때문에 한 번 호출은 된다.
         *  서블릿 3.0에서부터 비동기 방식의 요청 처리를 지원한다는 내용이니 너무 신경쓰지는 말자
         * */
        System.out.println("RequestListener:request attribute replaced!!");
        System.out.println(srae.getName() + ", " + srae.getValue());
    }

}