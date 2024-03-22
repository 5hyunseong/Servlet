package com.ohgiraffers.section01.commonsio;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.util.*;

@WebServlet("/commons/single")
public class CommonsFileUploadServlet extends HttpServlet {

    private String rootLocation;
    private int maxFileSize;
    private String encodingType;

    @Override
    public void init() throws ServletException {

        ServletContext context = getServletContext();

        rootLocation = context.getInitParameter("upload-location");
        maxFileSize = Integer.parseInt(context.getInitParameter("max-file-size"));
        encodingType = context.getInitParameter("encoding-type");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        /* 설명. commons fileupload는 내부에서 commons io 라이브러리와 의존관계에 있기 때문에 둘 다 받아야 한다.
         *  여기서는 input file 태그가 한 개이든 여러 개이든, 혹은 multiple 속성이 있든 상관없이 모두 커버되도록 작성한다.
         * */
        if(JakartaServletFileUpload.isMultipartContent(req)) {

            System.out.println("파일 저장 ROOT 경로 : " + rootLocation);
            System.out.println("최대 업로드 파일 용량 : " + maxFileSize);
            System.out.println("인코딩 방식 : " + encodingType);

            String fileUploadDirectory = rootLocation + "/commons";

            File directory = new File(fileUploadDirectory);

            /* 필기. 파일 저장경로가 존재하지 않으면 디렉토리를 생성한다. */
            if(!directory.exists()) {

                /* 필기. 폴더를 한 개만 생성할거면 mkdir, 상위 폴더도 존재하지 않아 한번에 생성할 때는 mkdirs를 이용한다. */
                System.out.println("폴더 생성 : " + directory.mkdirs());
            }

            /* 설명. 아래는 최종적으로 request를 parsing하고 파일을 저장한 뒤 필요한 내용을 담을 리스트와 맵이다.
             *      파일에 대한 정보는 리스트에, 다른 파라미터의 정보는 모두 맵에 담을 것이다.
             * */
            Map<String, String> parameter = new HashMap<>();
            List<Map<String, String>> fileList = new ArrayList<>();

            /* 필기. 파일을 업로드할 때 최대 크기나 임시 저장할 폴더의 경로 등을 포함하기 위한 인스턴스이다. */
            DiskFileItemFactory.Builder fileItemFactory = new DiskFileItemFactory.Builder();
            fileItemFactory.setPath(new File(fileUploadDirectory).getPath());
            fileItemFactory.setBufferSize(maxFileSize);
//            fileItemFactory.setRepository(new File(fileUploadDirectory));
//            fileItemFactory.setSizeThreshold(maxFileSize);

            /* 설명. 서블릿에서 기본 제공하는 기능 말고 꼭 commons fileupload 라이브러이에 있는 클래스로 import 해야 한다. */
            JakartaServletFileUpload fileUpload = new JakartaServletFileUpload(fileItemFactory.get());
//	        fileUpload.setHeaderEncoding(encodingType);		//별 의미 없는 듯 하다. 기본값은 null인데 파일명은 자동으로 UTF-8로 인코딩한 거 같다.

            try {
                /* 필기. request를 파싱하여 데이터 하나 하나를 FileItem 인스턴로 반환한다. */
                List<FileItem> fileItems = fileUpload.parseRequest(req);

                for(FileItem item : fileItems) {
                    /* 필기. 폼 데이터는 isFormField 속성이 true이고, 파일은 isFormField 속성이 false이다. */
                    System.out.println(item);
                }

                /* 설명. 위에서 출력해본 모든 item들을 다 처리할 것이다. */
                for(int i = 0; i < fileItems.size(); i++) {
                    FileItem item = fileItems.get(i);

                    if(!item.isFormField()) {

                        /* 설명. 파일 데이터인 경우 */
                        if(item.getSize() > 0) {

                            /* 설명. 파일의 사이즈가 0보다 커야 전송된 파일이 있다는 의미이다.
                             *      전송된 파일이 있는 경우에만 처리하고, 0인 경우에는 무시하도록 로직을 작성한다.
                             * */
                            String filedName = item.getFieldName();
                            String originFileName = item.getName();

                            int dot = originFileName.lastIndexOf(".");
                            String ext = originFileName.substring(dot);

                            String randomFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                            /* 설명. 저장할 파일 정보를 담은 인스턴스를 생성하고 */
                            File storeFile = new File(fileUploadDirectory + "/" + randomFileName);

                            /* 설명. 저장한다 */
                            item.write(storeFile.toPath());

                            /* 설명. 필요한 정보를 Map에 담는다. */
                            Map<String, String> fileMap = new HashMap<>();
                            fileMap.put("filedName", filedName);
                            fileMap.put("originFileName", originFileName);
                            fileMap.put("savedFileName", randomFileName);
                            fileMap.put("savePath", fileUploadDirectory);

                            fileList.add(fileMap);

                        }

                    } else {
                        /* 필기. 폼 데이터인 경우 */
                        /*      전송된 폼의 name은 getFiledName()으로 받아오고, 해당 필드의 value는 getString()으로 받아온다.
                         *      하지만 인코딩 설정을 하더라도 전송되는 파라미터는 ISO-8859-1로 처리된다.
                         *      별도로 ISO-8859-1로 해석된 한글을 UTF-8로 변경해야 한다.
                         * */
//						parameter.put(item.getFieldName(), item.getString());
                        parameter.put(item.getFieldName(), new String(item.getString().getBytes("ISO-8859-1"), "UTF-8"));

                    }
                }

                System.out.println("parameter : " + parameter);
                System.out.println("fileList : " + fileList);

            } catch (Exception e) {
                // 설명. 어떤 종류의 Exception이 발생 하더라도 실패하면 파일을 삭제해야 한다.
                int cnt = 0;
                for(int i = 0; i < fileList.size(); i++) {
                    Map<String, String> file = fileList.get(i);

                    File deleteFile = new File(fileUploadDirectory + "/" + file.get("savedFileName"));
                    boolean isDeleted = deleteFile.delete();

                    if(isDeleted) {
                        cnt++;
                    }
                }

                if(cnt == fileList.size()) {
                    System.out.println("업로드에 실패한 모든 사진 삭제 완료!");
                } else {
                    e.printStackTrace();
                }

            }

        }
    }

}
