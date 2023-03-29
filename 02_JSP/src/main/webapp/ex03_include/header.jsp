<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	request.setCharacterEncoding("UTF-8");
	Optional<String> opt = Optional.ofNullable(request.getParameter("title"));
	String title = opt.orElse("환영합니다");z
<title><%=title%></title>
<%-- request.getContextPath() == /02_Jsp --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css">  <!-- css 파일 가져오기  --> <!-- contextpath는 webapp  -->
<script src="<%=request.getContextPath()%>/resources/js/lib/jquery-3.6.4.min.js"></script>
</head>
<body>
	
	<nav>  <%-- 네비게이션(gnb) 만들어주는 태그 (의미를 위해 사실상 nav인 <div>) --%>
		<ul>
			<li><a href="body1.jsp">body1</a></li>
			<li><a href="body2.jsp">body2</a></li>
			<li><a href="body3.jsp">body3</a></li>
		</ul>
	</nav>

	<hr>
	
	