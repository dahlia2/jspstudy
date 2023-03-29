<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
	// session에 저장된 cart 속성 지우기 (session 전체를 지우면 로그인이 풀림)
	session.removeAttribute("cart");
	
	// 다시 쇼핑하러 가기
	response.sendRedirect("03_cart_list.jsp");
	%>
	
</body>
</html>