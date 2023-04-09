<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<div>
		<h1>신규학생 등록 화면</h1>
		<div>
			<label for="name">이름</label>
			<input type="text" id="name" name="name" value="홍길동">
			
		</div>
	</div>
<body>

</body>
</html>