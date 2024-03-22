<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ServiceMethod</title>
</head>
<body>
	<h1>Chapter2. Service Method</h1>

	<h3>1. GET 방식의 요청</h3>
	<h4>a태그의 href 속성값 변경</h4>
	<a href="request-service">서비스 메소드 요청하기</a>
	<h4>form 태그의 method 속성을 get으로 설정</h4>
	<form action="request-service" method="get">
		<input type="submit" value="GET방식 요청 전송">
	</form>

	<h3>2. POST 방식의 요청</h3>
	<h4>form 태그의 method 속성을 post로 설정</h4>
	<form action="request-service" method="post">
		<input type="submit" value="POST방식 요청 전송">
	</form>

	<!--
		설명.
		 서블릿을 배울 때, 요청은 크게 GET과 POST로 나뉘어 진행된다.
		 1. GET:
		  GET 요청은 조회 시 사용되는 요청 형식이고, URL 경로에 노출되는 방식으로 서버에 값을 넘기며 조회하게 된다.
		  (쿼리스트링, PathVariable)
		 2. POST:
		  조회를 제외한 나머지(DML)를 진행할 때 주로 사용하며 경우에 따라 조회 시 URI 경로에 노출되지 않고
		  서버에 값을 넘기거나 조회를 위해 서버로 넘기는 값의 크기가 클 때(255byte 초과) POST 요청을 진행하게 된다.
		 --------------------------------------------------------------------------------------------------------------
		 REST API 방식으로 요청 시에는 더 세분화된 HTTP Method들(7가지)을 사용하게 되겠지만,
		 현재는 이 두 가지 방식의 요청에만 집중하자.
	-->
</body>
</html>