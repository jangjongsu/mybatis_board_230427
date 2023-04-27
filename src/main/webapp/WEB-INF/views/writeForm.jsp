<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글쓰기</title>
</head>
<body>
	<%
		String sessionId = (String)session.getAttribute("sessionId");
		if (sessionId == null) {
	%>
			<script type="text/javascript">
				alert('로그인 한 회원만 글쓰기가 가능합니다.')	
				location.href="login";
			</script>
			
	<%	
		}
	
	%>

</body>
</html>