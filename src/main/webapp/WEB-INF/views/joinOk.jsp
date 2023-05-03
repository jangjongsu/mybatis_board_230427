<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 성공</title>
</head>
<body>
	<%
		String checkId = request.getAttribute("checkIdFlag").toString();
		if(checkId.equals("1")){
	%>
			<script type="text/javascript">
				alert('입력하신 아디이는 이미 사용중 입니다. 다른아니디를 입력햐주세요!');
				history.go(-1);
			</script>
	<%
			
		} else if(checkId.equals("0")){
	%>
			<script type="text/javascript">
				alert('입력하신 아디이는 사용가능한 아이디 입니다.');
				history.go(-1);
			</script>
	<%
		}
	
	%>


	${memberName } 님 가입 환영합니다.<br>
	게시판에 열심히 글을 써주세요.<br>
	<a href="writeForm">게시판 바로가기</a><br><br>
	<a href="login">로그아웃</a>
</body>
</html>