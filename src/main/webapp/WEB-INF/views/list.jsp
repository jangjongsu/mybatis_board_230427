<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
</head>
<body>
	<h2>자유게시판 글 리스트</h2>
	<hr>
	<h3>총게시글 수 : ${total}</h3>
	<table border="1" cellspacing="0" cellpadding="0" width="1000">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>글쓴이</th>
			<th width="600">제목</th>
			<th>조회수</th>
			<th>등록일</th>
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr>
			<th>${dto.fnum }</th>
			<th>${dto.fid }</th>
			<th>${dto.fname }</th>
			<th>
				<a href="content_view?fnum=${dto.fnum}">${dto.ftitle }</a>
			</th>
			<th>${dto.fhit }</th>
			<th>${dto.fdate }</th>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="6"><a href="writeForm">글쓰기</a></td>
		</tr>
	
	
	</table>

</body>
</html>