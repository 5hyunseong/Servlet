package com.ohgiraffers.section02.thumbnail;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

@WebServlet("/transferToThumbnail")
public class TransferToThumbnailImageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        /* 필기. 썸네일 게시판 형태인 경우 원본 파일을 로드해서 사용자에게 보여주면 로딩도 느려지고,
         *  모바일로 접속하면 많은 양의 데이터 손실이 일어날 수 있다.
         *  따라서 썸네일 게시판 이미지용으로 이미지를 변환하여 보여주는 작업이 필요하다.
         *  여기서는 썸네일을 변환하는 thumbnailator 라이브러리를 이용하여 이미지를 변환할 것이다.
         *  https://coobird.github.io/thumbnailator/javadoc/0.4.13/ 자세한 사용 용법은 API를 참고하면된다.
         * */

        /* 필기. 톰캣 컨테이너의 최상위 경로를 알고 싶은 경우 사용한다. (web폴더의 경로이다.) */
        // ※※※ 확인 필요 -> 아래처럼 경로 받아오면 프로젝트가 아닌 tomcat 폴더 하위 경로로 찍힘
        System.out.println("[Path] " + req.getSession().getServletContext().getRealPath("/WEB-INF/"));

        // ※※※ 프로젝트 내에 저장하기 위해 임의로 물리적 절대경로 사용
        //String originFilePath = req.getSession().getServletContext().getRealPath("/") + "resources/origin-image/mybatis.PNG";
        String originFilePath = "C:\\myWs\\myIntelliJWs\\03_Servlet\\chap13-file-io-lecture-source\\src\\main\\resources\\origin-image\\mybatis.PNG";             // 수업 시 생성한 프로젝트 절대경로로 잡아주면 됨
        File originFile = new File(originFilePath);

        // ※※※ 프로젝트 내에 저장하기 위해 임의로 물리적 절대경로 사용
        //String savePath = req.getSession().getServletContext().getRealPath("/") + "resources/thumbnail-image/";
        String savePath = "C:\\myWs\\myIntelliJWs\\03_Servlet\\chap13-file-io-lecture-source\\src\\main\\resources\\origin-image\\";       // 수업 시 생성한 프로젝트 절대경로로 잡아주면 됨
        File thumbFilePath = new File(savePath);
        if(!thumbFilePath.exists()) {
            thumbFilePath.mkdirs();
        }
        String thumbFileName = "mybatis_thumbnail.jpg";

        try {
            Thumbnails.of(originFile)
                    .size(100, 80)
                    .toFile(savePath + thumbFileName);
            System.out.println("[SAVEPATH] thumbFile : " + (thumbFilePath + thumbFileName));
            System.out.println("썸네일 변환 성공!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
