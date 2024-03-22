<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>Request Parameter</title>
</head>
<body>
	<!-- HTML 수업을 듣지 않았다면 아래 내용이 부담스럽기 때문에 복붙 가능. -->
	<h1>Chapter 03. Request Parameter</h1>

	<h3>1. GET 방식의 요청</h3>
	<h4>1) form태그를 이용한 get 방식 요청</h4>
	<form action="querystring" method="get">
		<label>이름 : </label><input type="text" name="name">
        <br>
		<label>나이 : </label><input type="number" name="age">
        <br>
		<label>생일 : </label><input type="date" name="birthday">
        <br>
		<label>성별 : </label>
		<input type="radio" name="gender" id="male" value="M"><label for="male">남자</label>
		<input type="radio" name="gender" id="female" value="F"><label for="female">여자</label>
        <br>
		<label>국적 : </label>
		<select name="national">
			<option value="ko">한국</option>
			<option value="ch">중국</option>
			<option value="jp">일본</option>
			<option value="etc">기타</option>
		</select>
		<br>
		<label>취미 : </label>
		<input type="checkbox" name="hobbies" id="movie" value="movie"><label for="movie">영화</label>
		<input type="checkbox" name="hobbies" id="music" value="music"><label for="music">음악</label>
		<input type="checkbox" name="hobbies" id="sleep" value="sleep"><label for="sleep">취침</label>
		<br>
		<%--<input type="submit" value="GET 요청">--%>
		<button>GET 요청</button>
		<%-- button 태그는 type이라는 속성을 정의하지 않으면 기본적으로 submit 버튼임. --%>
	</form>

	<h4>2) a태그의 href 속성에 직접 파라미터를 쿼리스트링 형태로 작성하여 get 방식 요청</h4>
	<a href="querystring?name=홍길동&age=20&birthday=2021-01-08&gender=M&national=ko&hobbies=movie&hobbies=music&hobbies=sleep">
		쿼리스트링을 이용한 값 전달</a>

	<h4>3) form태그를 이용한 post 방식 요청</h4>
	<form action="formdata" method="post">
		<label>이름 : </label><input type="text" name="name">
		<br>
		<label>나이 : </label><input type="number" name="age">
		<br>
		<label>생일 : </label><input type="date" name="birthday">
		<br>
		<label>성별 : </label>
		<input type="radio" name="gender" id="male2" value="M"><label for="male2">남자</label>
		<input type="radio" name="gender" id="female2" value="F"><label for="female2">여자</label>
		<br>
		<label>국적 : </label>
		<select name="national">
			<option value="ko">한국</option>
			<option value="ch">중국</option>
			<option value="jp">일본</option>
			<option value="etc">기타</option>
		</select>
		<br>
		<label>취미 : </label>
		<input type="checkbox" name="hobbies" id="movie2" value="movie"><label for="movie2">영화</label>
		<input type="checkbox" name="hobbies" id="music2" value="music"><label for="music2">음악</label>
		<input type="checkbox" name="hobbies" id="sleep2" value="sleep"><label for="sleep2">취침</label>
		<br>

		<input type="submit" value="POST 요청">
	</form>
</body>
</html>