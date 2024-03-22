package com.ohgiraffers.mvc.common.filter;

import com.ohgiraffers.mvc.common.config.ConfigLocation;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class ContextConfigFilter implements Filter {

    public ContextConfigFilter() {
    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        /* 설명. DB 접속 설정 정보 파일의 경로 정보가 비어있는 경우 초기화 해준다. */
        if(ConfigLocation.CONNECTION_CONFIG_LOCATION == null) {
            String root = request.getServletContext().getRealPath("/");
            String connectionInfoPath = request.getServletContext().getInitParameter("connection-info");

            System.out.println("DB접속경로 설정 완료");
//            ConfigLocation.CONNECTION_CONFIG_LOCATION = root + "/" + connectionInfoPath;
            ConfigLocation.CONNECTION_CONFIG_LOCATION = "C:\\backup\\04)SERVLET_BACKUP\\chap14-mvc-lecture-source\\src\\main\\webapp\\WEB-INF\\config\\connection-info.properties";
        }

        /* DAO 작성하다가 이 쪽을 추가할 것~ 미리 해도 상관 없긴 하고.. */
        if(ConfigLocation.MAPPER_LOCATION == null) {
            String root = request.getServletContext().getRealPath("/");
            String mapperLocation = request.getServletContext().getInitParameter("mapper-location");

            System.out.println("매퍼 경로 설정 완료");
            ConfigLocation.MAPPER_LOCATION = "C:\\backup\\04)SERVLET_BACKUP\\chap14-mvc-lecture-source\\src\\main\\webapp\\WEB-INF\\mapper\\";
        }

        /* 설명. 이렇게 경로를 static에 저장하면 템플릿에서도 사용이 가능하다. */
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {

    }

}
