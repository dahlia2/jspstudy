<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src=${contextPath}/resources/js/lib.jquery-3.6.4.min.js"></script>
<script>


	<a href="${contextPath}/write.post">새 포스트 작성</a>
	
	<hr>
	
	<!-- css랑 부트스트랩 이용해서 연습해보기 -->
	<div class="container">
			<ul>  <!-- for문에 id 쓰지 않기 주의 -->
 				<li>포스트 번호 ${post.post_no}</li>
				<li>작성자 ${post.writer}</li>
				<li>제목 ${post.title}</li>
				<li>내용 ${post.content}</li>
				<li>Ip ${post.ip}</li>
				<li>작성일 ${post.created_date}</li>
				<li>수정일 ${post.modifed_date}</li>
			</ul>
	</div>

</body>
</html>