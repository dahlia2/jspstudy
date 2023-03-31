<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- request에 저장되어 있는 건 EL 사용 가능 -->
	
	<c:if test="${today != null }">
		<h1>오늘은 ${today}입니다.</h1> 	 // request 응답을 setAttribute으로 저장한 today
	</c:if>
	<c:if test="${age != null}">
		<h1>나이는 ${age}입니다.</h1>
	</c:if>
	
	<c:if test="${bmi != null}">
		<h1>BMI ${bmi}는 ${health}입니다.</h1>
	</c:if>
	
	
</body>
</html>