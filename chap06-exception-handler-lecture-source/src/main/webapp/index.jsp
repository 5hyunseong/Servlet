<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>ExceptionHandler</title>
</head>
<body>
  <h1>Chapter 6. Exception Handler</h1>
  <!--
    Exception 핸들링 해보기.
    1. web.xml을 통해 에러 상태 코드 발생 시 어떤 servlet으로 요청할 것인지에 대한 url 경로 매핑해보기.
    2. request 객체의 attribute에 담긴 값을 활용해서 모든 에러에 대해 페이지를 동적으로 처리해 응답해보기.
  -->
  <ul>
    <li><a href="show404error">1) 404 에러 확인</a></li>
    <li><a href="show500error">2) 500 에러 확인</a></li>
  </ul>
</body>
</html>